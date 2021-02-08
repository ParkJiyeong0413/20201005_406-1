package com.jquery.service;

import java.sql.SQLException;
import java.util.Map;

import com.jquery.command.SearchCriteria;
import com.jquery.dto.ReplyVO;

public interface ReplyService {
	
		//리스트보기
		Map<String,Object> getReplyList(int bno,SearchCriteria cri)throws SQLException;	
		
		//리스트개수
		int getReplyListCount(int bno)throws SQLException;
		
		//등록
		void registReply(ReplyVO reply)throws SQLException;
			
		//수정
		void modifyReply(ReplyVO reply)throws SQLException;
		
		//삭제
		void removeReply(int rno)throws SQLException;
}
