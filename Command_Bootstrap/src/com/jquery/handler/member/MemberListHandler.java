package com.jquery.handler.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jquery.command.SearchCriteria;
import com.jquery.handler.CommandHandler;
import com.jquery.service.MemberService;
import com.jquery.service.MemberServiceImpl;

public class MemberListHandler implements CommandHandler {
	
	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url="member/list";
		
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





