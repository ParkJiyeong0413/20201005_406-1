package com.jquery.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jquery.dto.AttachVO;

public class AttachDAOImpl implements AttachDAO{
	
		
	@Override
	public void insertAttach(SqlSession session,AttachVO attach) throws SQLException {
		session.update("Attach-Mapper.insertAttach",attach);
	}

	@Override
	public void deleteAttach(SqlSession session,int ano) throws SQLException {
		session.update("Attach-Mapper.deleteAttach",ano);		
	}

	@Override
	public List<AttachVO> selectAttachesByBno(SqlSession session,int bno) throws SQLException {
		List<AttachVO> attachList=session.selectList("Attach-Mapper.selectAttachByBno",bno);
		return attachList;
	}

	@Override
	public void deleteAllAttach(SqlSession session,int pno) throws SQLException {
		session.update("Attach-Mapper.deleteAllAttach",pno);		
	}
	@Override
	public AttachVO selectAttachByAno(SqlSession session,int ano) throws SQLException {
		
		AttachVO attach=session.selectOne("Attach-Mapper.selectAttachByAno",ano);
		return attach;
	}

}
