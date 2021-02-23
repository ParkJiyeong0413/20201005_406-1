package com.jquery.handler.board;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jquery.dto.AttachVO;
import com.jquery.dto.BoardVO;
import com.jquery.handler.CommandHandler;
import com.jquery.service.BoardService;
import com.jquery.service.BoardServiceImpl;
import com.jquery.utils.GetUploadPath;
import com.jquery.utils.MakeFileName;
import com.jquery.utils.ServletFileUploadBuilder;

public class BoardModifyHandler implements CommandHandler {
	
	private BoardService boardService;
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

	
	

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BoardVO board = null;
		try {
			board = modifyFile(request, response);
			boardService.modify(board);

			response.getWriter().print(HttpServletResponse.SC_OK);
			
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		} finally {
			response.getWriter().print(board.getBno());
		}
		
		
		return null;
	}
	
	// 업로드 파일 환경 설정
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 200; // 200MB

	private BoardVO modifyFile(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ServletFileUpload upload = 
				ServletFileUploadBuilder.build(request, MEMORY_THRESHOLD, MAX_FILE_SIZE,MAX_REQUEST_SIZE);
		
		// 실제 저장 경로를 설정.
		String uploadPath = GetUploadPath.getUploadPath("attach.upload");

		File file = new File(uploadPath);
		if (!file.mkdirs()) {
			System.out.println(uploadPath + "가 이미 존재하거나 생성을 실패했습니다.");
		}

	
		// request로 부터 받는 파일을 추출해서 저장.
		BoardVO board = new BoardVO();
		List<AttachVO> attachList = new ArrayList<AttachVO>();			
		
		List<FileItem> formItems = upload.parseRequest(request);
		if (formItems != null && formItems.size() > 0) {
			List<String> deleteFiles = new ArrayList<String>();
			for (FileItem item : formItems) {
				if (!item.isFormField()) { // board : attachList
					// summernote의 files 를 제외함.
					if (!item.getFieldName().equals("uploadFile"))
						continue;

					String fileName = new File(item.getName()).getName();
					fileName = MakeFileName.toUUIDFileName(fileName, "$$");
					String filePath = uploadPath + File.separator + fileName;
					File storeFile = new File(filePath);

					// local HDD 에 저장.
					item.write(storeFile);

					// DB에 저장할 attach에 file 내용 추가.
					AttachVO attach = new AttachVO();
					attach.setFileName(fileName);
					attach.setUploadPath(uploadPath);
					attach.setFileType(fileName.substring(fileName.lastIndexOf(".") + 1));

					attachList.add(attach);

					System.out.println("upload file : " + attach);					
					
				} else { //board : bno, title, writer, content, deleteFile
					switch (item.getFieldName()) {
					case "title":
						board.setTitle(item.getString("utf-8"));
						break;
					case "writer":
						board.setWriter(item.getString("utf-8"));
						break;
					case "content":
						board.setContent(item.getString("utf-8"));
						break;
					case "bno":
						board.setBno(Integer.parseInt(item.getString("utf-8")));
						break;
					case "deleteFile":
						int ano = Integer.parseInt(item.getString("utf-8"));

						AttachVO attach = boardService.getAttachVO(ano);

						boardService.removeAttachAno(ano); // DB 삭제

						String filePath = attach.getUploadPath() + File.separator + attach.getFileName();
						File targetFile = new File(filePath);

						if (targetFile.exists()) {
							targetFile.delete(); // 파일 삭제
						}

						deleteFiles.add(item.getString("utf-8"));

						break;
					}
				}
			}

			board.setAttachList(attachList);

		System.out.println("receive board : \n" + board);
		System.out.println("deleteFile : \n" + deleteFiles);
		}
		return board;
	}

}
