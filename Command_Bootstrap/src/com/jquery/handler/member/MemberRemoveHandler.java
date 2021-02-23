package com.jquery.handler.member;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jquery.dto.MemberVO;
import com.jquery.handler.CommandHandler;
import com.jquery.service.MemberService;
import com.jquery.service.MemberServiceImpl;
import com.jquery.utils.GetUploadPath;

public class MemberRemoveHandler implements CommandHandler {
	
	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService=memberService;
	}
	


	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url=null;
		
		String id = request.getParameter("id");
		
		
		MemberVO member = memberService.getMember(id);
		
		//이미지 삭제
		String savedPath=GetUploadPath.getUploadPath("member.picture.upload");
		String fileName=member.getPicture();
		File picture = new File(savedPath,fileName);
		
		if(picture.exists()) {
			picture.delete();
		}
		
		//DB삭제
		memberService.remove(id);
		
		//로그인 유저 확인
		// 삭제되는 회원이 로그인 회원인경우 로그아웃 해야함.
		MemberVO loginUser = (MemberVO) request.getSession().getAttribute("loginUser");
		if (loginUser.getId().equals(member.getId())) {
			request.getSession().invalidate(); //session 갱신
		}
		
		//화면결정
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println("<script>");
		out.println("alert('아이디"+id+"님을 삭제했습니다'); ");
		out.println("window.opener.parent.location.reload(true);window.close();");
		out.println("</script>");
		
		return url;
	}
	
}




