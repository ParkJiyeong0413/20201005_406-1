package com.jquery.handler;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jquery.dto.MemberVO;
import com.jquery.service.MemberService;
import com.jquery.service.MemberServiceImpl;

public class MemberDetailHandler implements CommandHandler {
	
	private MemberService memberService=new MemberServiceImpl();
	public void setMemberService(MemberService memberService) {
		this.memberService=memberService;
	}
	
	@Override
	public boolean isRedirect(HttpServletRequest request) {
		return false;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url="/WEB-INF/views/member/detail.jsp";
		
		String id = request.getParameter("id");

		MemberVO member = null;
		
		try {
			member = memberService.getMember(id);
			request.setAttribute("member", member);
		} catch (SQLException e) {
			e.printStackTrace();
			url=null;
		}
		return url;
	}

}








