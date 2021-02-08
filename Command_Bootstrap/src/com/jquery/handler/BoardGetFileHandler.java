package com.jquery.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jquery.dto.AttachVO;
import com.jquery.service.BoardService;
import com.jquery.service.BoardServiceImpl;
import com.jquery.utils.MakeFileName;

public class BoardGetFileHandler implements CommandHandler {

	private BoardService boardService = BoardServiceImpl.getInstance();

	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

	@Override
	public boolean isRedirect(HttpServletRequest request) {
		return false;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 1.입력 (parameter : ano, bno)
		int bno = Integer.parseInt(request.getParameter("bno"));
		int ano = Integer.parseInt(request.getParameter("ano"));

		// 2. 처리 (파일정보,파일 로딩)
		AttachVO attach = boardService.getAttachVO(ano);
		String filePath = attach.getUploadPath() + File.separator + attach.getFileName();

		File sendFile = new File(filePath);
		FileInputStream inStream = new FileInputStream(sendFile);

		// 3. 파일전송

		sendFile(inStream, response.getOutputStream(), sendFile, request, response);
		return null;
	}

	private void sendFile(InputStream in, OutputStream out, File sendFile, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 1. contentType/Header setting
		ServletContext context = request.getServletContext();
		// 파일 포맷으로 MIME를 결정한다.
		String mimeType = context.getMimeType(sendFile.getName()); //jpg 형식 : image/jpeg
		if (mimeType == null) {
			mimeType = "application/octet-stream"; // 무조건 다운로드

		}

		// response 수정.		
		response.setContentType(mimeType);
		response.setContentLength((int) sendFile.length());

		// 파일명 한글깨짐 방지 : utf-8 → ISO-8859-1
		String downloadFileName = new String(sendFile.getName().getBytes("utf-8"), "ISO-8859-1");
		downloadFileName = MakeFileName.parseFileNameFromUUID(downloadFileName, "\\$\\$");

		// response header setting
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", downloadFileName);
		response.setHeader(headerKey, headerValue);
		
		
		// 파일 내보내기
		try {
			byte[] buffer = new byte[4096];
			int bytesRead = -1;
	
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
		}finally {
			if(in!=null)in.close();
			if(out!=null)out.close();
		}
	}

}
