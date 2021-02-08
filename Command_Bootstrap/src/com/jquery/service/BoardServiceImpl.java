package com.jquery.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jquery.command.PageMaker;
import com.jquery.command.SearchCriteria;
import com.jquery.dao.AttachDAO;
import com.jquery.dao.AttachDAOImpl;
import com.jquery.dao.BoardDAO;
import com.jquery.dao.BoardDAOImpl;
import com.jquery.dao.ReplyDAO;
import com.jquery.dao.ReplyDAOImpl;
import com.jquery.dto.AttachVO;
import com.jquery.dto.BoardVO;
import com.jquery.mybatis.OracleIBatisSqlSessionFactory;

public class BoardServiceImpl implements BoardService {

	private static BoardServiceImpl instance = new BoardServiceImpl();

	private BoardServiceImpl() {
	}

	public static BoardServiceImpl getInstance() {
		return instance;
	}

	private SqlSessionFactory sqlSessionFactory = OracleIBatisSqlSessionFactory.getSqlSessionFactory();
	public void setSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	private BoardDAO boardDAO = new BoardDAOImpl();
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

	private AttachDAO attachDAO = new AttachDAOImpl();
	public void setAttachDAO(AttachDAO attachDAO) {
		this.attachDAO = attachDAO;
	}

	private ReplyDAO replyDAO = new ReplyDAOImpl();

	public void setReplyDAO(ReplyDAO replyDAO) {
		this.replyDAO = replyDAO;
	}

	@Override
	public BoardVO getBoardForModify(int bno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession(false);
		try {
			BoardVO board = boardDAO.selectBoardByBno(session, bno);
			List<AttachVO> attachList = attachDAO.selectAttachesByBno(session, bno);
			board.setAttachList(attachList);
			session.commit();
			return board;
		} catch (SQLException e) {
			session.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public BoardVO getBoard(int bno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession(false);
		try {
			BoardVO board = boardDAO.selectBoardByBno(session, bno);
			List<AttachVO> attachList = attachDAO.selectAttachesByBno(session, bno);
			board.setAttachList(attachList);
			
			boardDAO.increaseViewCnt(session, bno);
			session.commit();
			return board;
		} catch (SQLException e) {
			session.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public void regist(BoardVO board) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession(false);
		try {
			int bno = boardDAO.selectBoardSeqNext(session);

			board.setBno(bno);

			boardDAO.insertBoard(session, board);

			for (AttachVO attach : board.getAttachList()) {
				attach.setBno(bno);
				attach.setAttacher(board.getWriter());
				attachDAO.insertAttach(session, attach);
			}
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
	public void modify(BoardVO board) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession(false);
		try {
			boardDAO.updateBoard(session, board);

			for (AttachVO attach : board.getAttachList()) {
				attach.setBno(board.getBno());
				attach.setAttacher(board.getWriter());
				attachDAO.insertAttach(session, attach);
			}
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
	public void remove(int bno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession(false);
		try {
			boardDAO.deleteBoard(session, bno);
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
	public Map<String, Object> getBoardList(SearchCriteria cri) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession(false);
		Map<String, Object> dataMap = new HashMap<String, Object>();

		try {
			// 현재 page 번호에 맞는 리스트를 perPageNum 개수 만큼 가져오기.
			List<BoardVO> boardList = boardDAO.selectBoardCriteria(session, cri);

			// reply count 입력
			for (BoardVO board : boardList) {
				int replycnt = replyDAO.countReply(session, board.getBno());
				board.setReplycnt(replycnt);
			}

			// 전체 board 개수
			int totalCount = boardDAO.selectBoardCriteriaTotalCount(session, cri);

			// PageMaker 생성.
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(totalCount);

			dataMap.put("boardList", boardList);
			dataMap.put("pageMaker", pageMaker);
			
			session.commit();
			
			return dataMap;
		} catch (SQLException e) {
			session.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public List<AttachVO> getAttachList(int bno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession(false);
		try {
			List<AttachVO> attachList = attachDAO.selectAttachesByBno(session, bno);
			return attachList;
		} catch (SQLException e) {
			session.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public AttachVO getAttachVO(int ano) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession(false);
		try {
			AttachVO attach = attachDAO.selectAttachByAno(session, ano);
			return attach;
		} catch (SQLException e) {
			session.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public void removeAttachAno(int ano) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession(false);
		try {
			attachDAO.deleteAttach(session, ano);	
			
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
