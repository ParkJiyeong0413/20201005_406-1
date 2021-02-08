package com.jquery.handler;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jquery.exception.NotMultipartFormDataException;
import com.jquery.utils.GetUploadPath;
import com.jquery.utils.MakeFileName;
import com.jquery.utils.ServletFileUploadBuilder;

public class SummernoteImgUploadHandler implements CommandHandler {

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return false;
	}
	
	// 업로드 파일 환경 설정
	private static final int MEMORY_THRESHOLD = 1024 * 500; // 500KB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 5; // 5MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 10; // 10MB
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ServletFileUpload upload=null;
		try {
			upload = ServletFileUploadBuilder.build(request,MEMORY_THRESHOLD, MAX_FILE_SIZE, MAX_REQUEST_SIZE);
		} catch (NotMultipartFormDataException e1) {
			System.out.println(e1.getMessage());
			return null;
		}
		
		
		//파일저장 경로
		String uploadPath = GetUploadPath.getUploadPath("summernote.img");
				
		
		//저장경로 확인 및 새로 만들기
		File file = new File(uploadPath);
		if (!file.mkdirs()) {
			System.out.println(uploadPath + "가 이미 존재하거나 실패했습니다.");		
		};
		
		try {
			List<FileItem> formItems = upload.parseRequest(request);
			// 파일 개수 확인.
			if (formItems != null && formItems.size() > 0) {
				for (FileItem item : formItems) { // form items 반복하여 꺼내는 구문 
					if (!item.isFormField()) { // 파일일 경우 해당
						// uuid+구분자+파일명
						String fileName = MakeFileName.toUUIDFileName(".jpg", "");
						String filePath = uploadPath + File.separator + fileName;
						File storeFile = new File(filePath);
						
						// local HDD 에 저장.
						item.write(storeFile);
						

						PrintWriter out = response.getWriter();
						/*String outStr=urlPath+"/"+fileName;*/
						String outStr=request.getContextPath()+"/common/summernote/getImg.do?fileName="+fileName;
						out.print(outStr);
						out.close();
					}
				}
			}
		} catch (Exception e) {			
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			
		}
		
		return null;
	}

}









