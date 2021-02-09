package com.jquery.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jquery.command.SearchCriteria;
import com.jquery.dto.MemberVO;

public class MemberDAOImpl implements MemberDAO {

	@Override
	public List<MemberVO> selectMemberList(SqlSession session, SearchCriteria cri) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectMemberListCount(SqlSession session, SearchCriteria cri) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MemberVO selectMemberById(SqlSession session, String id) throws SQLException {
		MemberVO member=session.selectOne("Member-Mapper.selectMemberById",id);			
		return member;
	}

	@Override
	public void insertMember(SqlSession session, MemberVO member) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateMember(SqlSession session, MemberVO member) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteMember(SqlSession session, String id) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void disabledMember(SqlSession session, String id) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void enabledMember(SqlSession session, String id) throws SQLException {
		// TODO Auto-generated method stub

	}

}
