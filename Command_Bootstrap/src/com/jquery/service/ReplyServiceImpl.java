package com.jquery.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jquery.command.PageMaker;
import com.jquery.command.SearchCriteria;
import com.jquery.dao.ReplyDAO;
import com.jquery.dao.ReplyDAOImpl;
import com.jquery.dto.ReplyVO;
import com.jquery.mybatis.OracleMyBatisSqlSessionFactory;

public class ReplyServiceImpl implements ReplyService {

	private SqlSessionFactory sqlSessionFactory ;
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	private ReplyDAO replyDAO;
	public void setReplyDAO(ReplyDAO replyDAO) {
		this.replyDAO = replyDAO;
	}

	@Override
	public Map<String, Object> getReplyList(int bno, SearchCriteria cri) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();

		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			List<ReplyVO> replyList = replyDAO.selectReplyListPage(session, bno, cri);

			int count = replyDAO.countReply(session, bno);

			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(count);

			dataMap.put("replyList", replyList);
			dataMap.put("pageMaker", pageMaker);

			return dataMap;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
	}
	@Override
	public int getReplyListCount(int bno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession(false);

		try {
			int count = replyDAO.countReply(session, bno);
			return count;
		} catch (SQLException e) {
			session.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
	}

	
	@Override
	public void registReply(ReplyVO reply) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession(false);

		try {
			int rno = replyDAO.selectReplySeqNextValue(session);
			reply.setRno(rno);

			replyDAO.insertReply(session, reply);
			session.commit();
		} catch (SQLException e) {
			session.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}

	}

	@Override
	public void modifyReply(ReplyVO reply) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession(false);

		try {
			replyDAO.updateReply(session, reply);
			session.commit();
		} catch (SQLException e) {
			session.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}

	}

	@Override
	public void removeReply(int rno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession(false);

		try {
			replyDAO.deleteReply(session, rno);
			session.commit();
		} catch (SQLException e) {
			session.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
	}

	
}
