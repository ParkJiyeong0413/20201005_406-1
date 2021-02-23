package com.jquery.handler.board;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jquery.command.SearchCriteria;
import com.jquery.handler.CommandHandler;
import com.jquery.service.BoardService;
import com.jquery.service.BoardServiceImpl;
import com.jquery.utils.JsonResolver;

public class BoardListHandler implements CommandHandler {
	
	private BoardService boardService ;
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
	ObjectMapper mapper=new ObjectMapper();
		
		SearchCriteria cri = mapper.readValue(request.getReader(), SearchCriteria.class);
		
				
		try {
			Map<String,Object> dataMap=boardService.getBoardList(cri);
			
			JsonResolver.view(response, dataMap);
			
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return null;
	}

}
