package com.jquery.handler;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jquery.dto.ReplyVO;
import com.jquery.service.ReplyService;
import com.jquery.service.ReplyServiceImpl;

public class BoardReplyModifyHandler implements CommandHandler {

	private ReplyService replyService = new ReplyServiceImpl();
	public void setReplyService(ReplyService replyService) {
		this.replyService= replyService;
	}
	
	
	@Override
	public boolean isRedirect(HttpServletRequest request) {
		return false;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 1. 입력
		String rno = request.getParameter("rno");
		String replyer = request.getParameter("replyer");
		String replytext = request.getParameter("replytext");
		
		try {
			ReplyVO reply = new ReplyVO();
			reply.setRno(Integer.parseInt(rno));
			reply.setReplyer(replyer);
			reply.setReplytext(replytext);
			reply.setUpdatedate(new Date());
			
			
			// 2. 처리		
			replyService.modifyReply(reply);
			
			// 3. 결과/출력
			response.getWriter().print(HttpServletResponse.SC_OK);
			
		}catch(Exception e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
			throw e;
		}
		
		
		
		return null;
	}

}










