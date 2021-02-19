package com.jquery.handler;

import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jquery.dto.MemberVO;
import com.jquery.service.MemberService;
import com.jquery.service.MemberServiceImpl;

public class MemberIdCheckHandler implements CommandHandler {
	
	private MemberService memberService=new MemberServiceImpl();
	public void setMemberService(MemberService memberService) {
		this.memberService=memberService;
	}
	
	@Override
	public boolean isRedirect(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url=null;
		
		String id=request.getParameter("id");
		
		MemberVO member=null;	
		
		try {
			member = memberService.getMember(id);
			
			if(member!=null) {
				PrintWriter out=response.getWriter();
				out.print("duplicated");
				out.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return url;
	}

}









