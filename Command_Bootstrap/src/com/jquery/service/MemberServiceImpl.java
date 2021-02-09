package com.jquery.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jquery.command.SearchCriteria;
import com.jquery.dao.MemberDAO;
import com.jquery.dao.MemberDAOImpl;
import com.jquery.dto.MemberVO;
import com.jquery.exception.InvalidPasswordException;
import com.jquery.exception.NotFoundIDException;
import com.jquery.mybatis.OracleIBatisSqlSessionFactory;

public class MemberServiceImpl implements MemberService {
	
	private SqlSessionFactory sqlSessionFactory=OracleIBatisSqlSessionFactory.getSqlSessionFactory() ;
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	private MemberDAO memberDAO=new MemberDAOImpl();
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	
	@Override
	public void login(String id, String pwd) throws SQLException, NotFoundIDException, InvalidPasswordException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			MemberVO member = memberDAO.selectMemberById(session, id);
			if (member == null)
				throw new NotFoundIDException();
			if (!pwd.equals(member.getPwd()))
				throw new InvalidPasswordException();
		} finally {
			session.close();
		}
		
	}
	
	@Override
	public List<MemberVO> getMemberList(SearchCriteria cri) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getSearchMemberList(SearchCriteria cri) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberVO getMember(String id) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			MemberVO member = memberDAO.selectMemberById(session, id);
			return member;
		} finally {
			session.close();
		}
	}

	@Override
	public void regist(MemberVO member) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void modify(MemberVO member) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(String id) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void disabled(String id) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void enabled(String id) throws SQLException {
		// TODO Auto-generated method stub

	}

	

}
