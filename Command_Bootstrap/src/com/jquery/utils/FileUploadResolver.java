package com.jquery.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

import com.jquery.dto.AttachVO;

public class FileUploadResolver {
	public static List<AttachVO> fileUpload(FileItem[] items,String uploadPath) 
																	throws Exception {
		
		List<AttachVO> attachList = new ArrayList<AttachVO>();
		
		File file = new File(uploadPath);
		if (!file.mkdirs()) {
			System.out.println(uploadPath + "가 이미 존재하거나 생성을 실패했습니다.");
		}
		
		
		if(items!=null) for (FileItem item : items) {
			if (!item.isFormField()) {
				String fileName = new File(item.getName()).getName();
				fileName = MakeFileName.toUUIDFileName(fileName, "$$");
				String filePath = uploadPath + File.separator + fileName;
				File storeFile = new File(filePath);
				
				// local HDD 에 저장.
				try {
					item.write(storeFile);
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}   
				
				// DB에 저장할 attach에 file 내용 추가.
				AttachVO attach = new AttachVO();
				attach.setFileName(fileName);
				attach.setUploadPath(uploadPath);
				attach.setFileType(fileName.substring(fileName.lastIndexOf(".") + 1));

				attachList.add(attach);

				System.out.println("upload file : " + attach);
			}
		}
		
		return attachList;
	}
}
