package com.jquery.handler.board;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jquery.command.PageMaker;
import com.jquery.command.ReplyRemoveCommand;
import com.jquery.command.SearchCriteria;
import com.jquery.handler.CommandHandler;
import com.jquery.service.ReplyService;
import com.jquery.service.ReplyServiceImpl;
import com.jquery.utils.JsonResolver;

public class BoardReplyRemoveHandler implements CommandHandler {
	
	private ReplyService replyService;
	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}
	

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ObjectMapper mapper=new ObjectMapper();		
		ReplyRemoveCommand removeReq 
			= mapper.readValue(request.getReader(), ReplyRemoveCommand.class);
		
		try {
			
			replyService.removeReply(removeReq.getRno());
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(new SearchCriteria());
			pageMaker.setTotalCount(replyService.getReplyListCount(removeReq.getBno()));	
			
			int realEndPage = pageMaker.getRealEndPage();	
			
			JsonResolver.view(response, realEndPage);
			
		}catch(SQLException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
   		}
		
		return null;
	}

}




