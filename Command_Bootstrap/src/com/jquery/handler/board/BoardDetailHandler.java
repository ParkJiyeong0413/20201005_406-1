package com.jquery.handler.board;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jquery.dto.AttachVO;
import com.jquery.dto.BoardVO;
import com.jquery.handler.CommandHandler;
import com.jquery.service.BoardService;
import com.jquery.service.BoardServiceImpl;
import com.jquery.utils.JsonResolver;
import com.jquery.utils.MakeFileName;

public class BoardDetailHandler implements CommandHandler {

	private BoardService boardService;
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String url="board/detail";
		
		//1. parameter 수용..
		int bno = Integer.parseInt(request.getParameter("bno"));
		String from = request.getParameter("from");
		
		try {
			//2. 처리 (boardService)
			BoardVO board=null;
			if(from!=null &&from.equals("modify")) {
				board = boardService.getBoardForModify(bno);
			}else {
				board = boardService.getBoard(bno);
			}		
			
			
			List<AttachVO> renamedAttachList=MakeFileName.parseFileNameFromAttaches(board.getAttachList(), "\\$\\$");
			board.setAttachList(renamedAttachList);
			
			
			//3. 결과 (JsonResolver : json)
			//JsonResolver.view(response, board);
			
			request.setAttribute("board", board);
			
		}catch(SQLException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
			throw e;
		}
		
		return url;
	}

}










