package com.jquery.handler.board;

import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jquery.command.PageMaker;
import com.jquery.command.ReplyRegistCommand;
import com.jquery.command.SearchCriteria;
import com.jquery.handler.CommandHandler;
import com.jquery.service.ReplyService;
import com.jquery.service.ReplyServiceImpl;

public class BoardReplyRegistHandler implements CommandHandler {
	
	private ReplyService replyService;
	public void setReplyService(ReplyService replyService) {
		this.replyService= replyService;
	}
	

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ObjectMapper mapper=new ObjectMapper();
		
		ReplyRegistCommand replyReq = mapper.readValue(request.getReader(), ReplyRegistCommand.class);
		
	
		
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		try {
			replyService.registReply(replyReq.toReplyVO());
			
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(new SearchCriteria());
			pageMaker.setTotalCount(replyService.getReplyListCount(replyReq.toReplyVO().getBno()));	
			
			int realEndPage = pageMaker.getRealEndPage();				
			out.println("SUCCESS,"+realEndPage);
			
		} catch (SQLException e) {
			out.print("FAIL,1");
			e.printStackTrace();
		}finally {
			if(out!=null)out.close();
		}	
		
		return null;
	}

}
