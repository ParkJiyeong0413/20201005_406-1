package com.jquery.handler.member;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jquery.dto.AttachVO;
import com.jquery.dto.MemberVO;
import com.jquery.handler.CommandHandler;
import com.jquery.service.MemberService;
import com.jquery.service.MemberServiceImpl;
import com.jquery.utils.FileUploadResolver;
import com.jquery.utils.GetUploadPath;
import com.jquery.utils.MultipartHttpServletRequestParser;

public class MemberModifyHandler implements CommandHandler {
	
	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService=memberService;
	}
	

	// 업로드 파일 환경 설정
	private static final int MEMORY_THRESHOLD = 1024 * 500; // 500KB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 1; // 1MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 2; // 2MB

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url=null;
		
		MultipartHttpServletRequestParser multiReq 
			= new MultipartHttpServletRequestParser(request, MEMORY_THRESHOLD, MAX_FILE_SIZE,
					MAX_REQUEST_SIZE);
		
		String id = multiReq.getParameter("id");
		String pwd = multiReq.getParameter("pwd");
		String email = multiReq.getParameter("email");
		String authority= multiReq.getParameter("authority");
		String name = multiReq.getParameter("name");
		String phone = multiReq.getParameter("phone");
		
		MemberVO member = new MemberVO();
		member.setId(id);
		member.setPwd(pwd);
		member.setPhone(phone);
		member.setEmail(email);
		member.setAuthority(authority);
		member.setName(name);
		

		//저장경로
		String uploadPath = GetUploadPath.getUploadPath("member.picture.upload");
		File file = new File(uploadPath);
		if (!file.mkdirs()) {
			System.out.println(uploadPath + "가 이미 존재하거나 생성을 실패했습니다.");
		}
		
		//기존 사진 변경 유무 확인
		String uploadPicture = multiReq.getParameter("uploadPicture");
		if(uploadPicture!=null && !uploadPicture.isEmpty()) { //사진 변경
			File deleteFile = new File(uploadPath,multiReq.getParameter("oldPicture"));
			if(deleteFile.exists()) {
				deleteFile.delete();
			}
			
			//사진이미지 저장
			List<AttachVO> attachList;
				attachList = FileUploadResolver.fileUpload(multiReq.getFileItems("picture"),
						uploadPath);
			String savedFileName = attachList.get(0).getFileName();
			member.setPicture(savedFileName);
		}else { //사진 변경되지 않았다.
			member.setPicture(multiReq.getParameter("oldPicture"));
		}
		
		//처리
		memberService.modify(member);
		
		//로그인 사용자 확인
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		String resultStr="<script>";
		
		if (member.getId().equals(loginUser.getId())) {
			member = memberService.getMember(member.getId());
			session.setAttribute("loginUser", member);
			
			resultStr += "window.opener.parent.location.reload();";
		}
		
		resultStr += "location.href='detail.do?id="+member.getId()+"';";
		resultStr += "</script>";
		
		PrintWriter out = response.getWriter();
		out.print(resultStr);
		out.close();
		
		return url;
	}

}
