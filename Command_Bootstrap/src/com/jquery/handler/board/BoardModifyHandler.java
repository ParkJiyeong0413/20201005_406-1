package com.jquery.handler.board;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import com.jquery.dto.AttachVO;
import com.jquery.dto.BoardVO;
import com.jquery.handler.CommandHandler;
import com.jquery.service.BoardService;
import com.jquery.utils.FileUploadResolver;
import com.jquery.utils.GetUploadPath;
import com.jquery.utils.MultipartHttpServletRequestParser;

public class BoardModifyHandler implements CommandHandler {

	private BoardService boardService;

	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

	// 업로드 파일 환경 설정
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 200; // 200MB

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "board/modify_success";

		return url;
	}

	private BoardVO modifyFile(HttpServletRequest request, HttpServletResponse response) throws Exception {

		BoardVO board = null;

		MultipartHttpServletRequestParser multi = new MultipartHttpServletRequestParser(request, MEMORY_THRESHOLD,
				MAX_FILE_SIZE, MAX_REQUEST_SIZE);

		FileItem[] fileItems = multi.getFileItems("uploadFile");

		// 추가된 파일 저장
		String uploadPath = GetUploadPath.getUploadPath("attach.upload");
		List<AttachVO> attachList = FileUploadResolver.fileUpload(fileItems, uploadPath);
		
		board = new BoardVO();
		board.setAttachList(attachList);
		board.setBno(Integer.parseInt(multi.getParameter("bno")));
		board.setContent(multi.getParameter("content"));
		board.setTitle(multi.getParameter("title"));
		board.setWriter(multi.getParameter("writer"));
		
		//파일 삭제 및 DB삭제
		String[] deleteFiles = multi.getParameterValues("deleteFile");
		if(deleteFiles!=null && deleteFiles.length>0) {
			for(String anoStr : deleteFiles) {
				int ano = Integer.parseInt(anoStr);
				AttachVO attach=boardService.getAttachVO(ano);
				
				String filePath = attach.getUploadPath() 
						+ File.separator + attach.getFileName();
				File targetFile = new File(filePath);
				
				if(targetFile.exists()) {
					targetFile.delete(); //파일 삭제
				}
				boardService.removeAttachAno(ano); //DB 삭제
			}
			
		}
		return board;
	}
}






