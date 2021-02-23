package com.jquery.handler.board;

import java.io.File;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jquery.dto.AttachVO;
import com.jquery.dto.BoardVO;
import com.jquery.handler.CommandHandler;
import com.jquery.service.BoardService;
import com.jquery.service.BoardServiceImpl;

public class BoardRemoveHandler implements CommandHandler {
	
	private BoardService boardService;
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int bno = Integer.parseInt(request.getParameter("bno"));

		try {
			
			BoardVO board = boardService.getBoard(bno);
			List<AttachVO> attachList = board.getAttachList();
			
			if(attachList != null) {
				for(AttachVO attach : attachList) {
					File deleteTarget = new File(attach.getUploadPath()+File.separator+attach.getFileName());
					
					if(deleteTarget.exists()) {
						deleteTarget.delete();
					}
				}
			}
			
			boardService.remove(bno);

			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제되었습니다');");
			out.println("window.opener.location.reload(true);window.close();");
			out.println("</script>");
			out.close();

		} catch (SQLException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return null;
	}

}
