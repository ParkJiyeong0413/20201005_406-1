package com.jquery.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.jquery.command.SearchCriteria;
import com.jquery.dto.MemberVO;

public class MemberDAOImpl implements MemberDAO {

	@Override
	public List<MemberVO> selectMemberList(SqlSession session, SearchCriteria cri) throws SQLException {
		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);

		List<MemberVO> memberList = null;
		memberList=session.selectList("Member-Mapper.selectSearchMemberList",cri,rowBounds);

		return memberList;
	}

	@Override
	public int selectMemberListCount(SqlSession session, SearchCriteria cri) throws SQLException {
		int count=0;		
		count=session.selectOne("Member-Mapper.selectSearchMemberListCount",cri);
		return count;
	}

	@Override
	public MemberVO selectMemberById(SqlSession session, String id) throws SQLException {
		MemberVO member=session.selectOne("Member-Mapper.selectMemberById",id);			
		return member;
	}

	@Override
	public void insertMember(SqlSession session, MemberVO member) throws SQLException {
		session.update("Member-Mapper.insertMember",member);

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
