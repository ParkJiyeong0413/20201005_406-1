package com.jquery.handler;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jquery.command.SearchCriteria;
import com.jquery.service.ReplyService;
import com.jquery.service.ReplyServiceImpl;
import com.jquery.utils.JsonResolver;


public class BoardReplyListHandler implements CommandHandler {
	

	private ReplyService replyService=new ReplyServiceImpl();
	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}
	
	@Override
	public boolean isRedirect(HttpServletRequest request) {
		return false;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 1. 입력
		int bno = Integer.parseInt(request.getParameter("bno"));
		int page = Integer.parseInt(request.getParameter("page"));

		SearchCriteria cri = new SearchCriteria();
		cri.setPage(page);
		cri.setPerPageNum(10);
		
		// 2. 처리
		try {
			Map<String,Object> dataMap = replyService.getReplyList(bno, cri);
			
			// 3. 결과 / 출력
			JsonResolver.view(response, dataMap);
		}catch(SQLException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
			throw e;
		}
		
		return null;
	}

}









