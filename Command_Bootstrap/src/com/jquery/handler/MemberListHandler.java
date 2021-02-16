package com.jquery.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jquery.command.SearchCriteria;
import com.jquery.service.MemberService;
import com.jquery.service.MemberServiceImpl;

public class MemberListHandler implements CommandHandler {
	
	private MemberService memberService=new MemberServiceImpl();
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@Override
	public boolean isRedirect(HttpServletRequest request) {
		return false;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url="/WEB-INF/views/member/list.jsp";
		
		//입력
		SearchCriteria cri = new SearchCriteria();
		
		String page = request.getParameter("page");
		String perPageNum = request.getParameter("perPageNum");
		String searchType = request.getParameter("searchType");
		String keyword = request.getParameter("keyword");
		
		cri.setPage(page);
		cri.setPerPageNum(perPageNum);		
		cri.setSearchType(searchType);
		cri.setKeyword(keyword);
		//처리
		Map<String, Object> dataMap = memberService.getSearchMemberList(cri);
		
		request.setAttribute("memberList", dataMap.get("memberList"));
		request.setAttribute("pageMaker", dataMap.get("pageMaker")); 
		//출력
		
		return url;
	}

}





