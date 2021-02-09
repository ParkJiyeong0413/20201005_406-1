package com.jquery.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jquery.command.SearchCriteria;
import com.jquery.dto.MemberVO;

public interface MemberDAO {
	
	// 회원리스트
	List<MemberVO> selectMemberList(SqlSession session,SearchCriteria cri) throws SQLException;

	// 검색 결과의 전체 리스트 개수
	int selectMemberListCount(SqlSession session,SearchCriteria cri) throws SQLException;

	// 회원정보 조회
	MemberVO selectMemberById(SqlSession session,String id) throws SQLException;

	// 회원정보 추가
	void insertMember(SqlSession session,MemberVO member) throws SQLException;

	// 회원정보 수정
	void updateMember(SqlSession session,MemberVO member) throws SQLException;

	// 회원정보 삭제
	void deleteMember(SqlSession session,String id) throws SQLException;

	// 회원정지
	void disabledMember(SqlSession session,String id) throws SQLException;

	// 회원 활성화
	void enabledMember(SqlSession session,String id) throws SQLException;
}
