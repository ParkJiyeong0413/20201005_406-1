package com.jquery.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jquery.exception.NotMultipartFormDataException;

public class MultipartHttpServletRequestParser {
	
	// 업로드 파일 환경 설정
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 200; // 200MB

	Map<String, String[]> paramString = new HashMap<String, String[]>();
	Map<String, List<FileItem>> paramFile = new HashMap<String, List<FileItem>>();
	
	

	public MultipartHttpServletRequestParser(HttpServletRequest request, int memory_threshold,
											 int max_file_size, int max_request_size)
						throws NotMultipartFormDataException, UnsupportedEncodingException, 
							   FileUploadException {

		ServletFileUpload upload = 
				ServletFileUploadBuilder.build(request, memory_threshold, max_file_size,
												max_request_size);

		List<FileItem> formItems = upload.parseRequest(request);

		for (FileItem item : formItems) {
			// 1.1 필드
			String paramName = item.getFieldName();

			if (item.isFormField()) {
				String[] paramValues = item.getString("utf-8").split(",");
				this.paramString.put(paramName, paramValues);

			} else {// 1.2 파일
				List<FileItem> files = this.paramFile.get(paramName);
				
				if (files == null) {
					files = new ArrayList<FileItem>();
					this.paramFile.put(paramName, files);
				}
				
				files.add(item);
			}
		}
	}

	public String[] getParameterValues(String paramName) {
		return this.paramString.get(paramName);
	}

	public String getParameter(String paramName) {
		return this.paramString.get(paramName)[0];
	}
	
	public FileItem getFileItem(String paramName) {
		return paramFile.get(paramName).get(0);
	}

	public FileItem[] getFileItems(String paramName) {
		List<FileItem> items = paramFile.get(paramName);
		FileItem[] files = new FileItem[items.size()];
		items.toArray(files);
		return files;
	}
	
	public String[] getParameterNames() {
		List<String> paramNames = new ArrayList<String>();

		if (paramString.size() > 0) {
			for (String paramName : paramString.keySet()) {
				paramNames.add(paramName);
			}
		}
		if (paramFile.size() > 0) {
			for (String paramName : paramFile.keySet()) {
				paramNames.add(paramName);
			}
		}

		String[] paramNamesArray = new String[paramNames.size()];
		paramNames.toArray(paramNamesArray);

		return paramNamesArray;
	}

}











