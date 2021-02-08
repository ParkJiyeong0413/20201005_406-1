package kr.or.ddit.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jquery.handler.CommandHandler;
import com.jquery.handler.NullHandler;

public class WebController extends HttpServlet {
	
	
	/*
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	

	private void process(HttpServletRequest req, HttpServletResponse resp) {
	  	
		// 요청 URL가져오기
		String reqURI = req.getRequestURI(); 
		
		// ContextPath 부분을 제거한 URL 가져오기
		if(reqURI.indexOf(req.getContextPath()) == 0) {
			reqURI = reqURI.substring(
					req.getContextPath().length());
		}
		
		if(reqURI.equals("/member/list.do")) {
			// 목록 조회
		}else if(reqURI.equals("/member/insert.do")) {
			if(req.getMethod().equals("GET")) {
				// 등록하는 폼화면으로 이동
			}else if(req.getMethod().equals("POST")) {
				// 등록작업 시작.
			}
		}else if(reqURI.equals("/member/update.do")) {
			if(req.getMethod().equals("GET")) {
				// 수정하는 폼화면으로 이동
			}else if(req.getMethod().equals("POST")) {
				// 수정작업 시작.
			}
		}else if(reqURI.equals("/member/delete.do")) {
			// 삭제 작업
		}
	*/
		
	/**
	 * - 커맨드 패턴...
	 * 사용자 요청에 대한 실제 처리 기능을 커맨드 객체로 처리하기
	 * 
	 * Command : 사용자 요청을 캡슐화한 객체(실제 처리기능을 구현한 객체)
	 * Invoker : 사용자 요청에 대응되는 적당한 커맨드 객체를 찾아 실행해
	 *           주는 역할 하는 객체
	 *           
	 *  장점
	 *  요청을 처리하는 객체(Invoker)로부터 실제 수행 기능을 분리함으로서
	 *  새로운 기능을 추가하는데 보다 수월하다.
	 *  => 새로운 기능(Command)를 추가할 때 기존 기능을 수정할 필요가
	 *     없다.(유지보수가 쉬워진다.)
	 */
     /////////////////////////////////////////////////
	
	// 매핑정보 저장
	private Map<String, CommandHandler> cmdHandlerMap
	  = new HashMap<String, CommandHandler>();
	
	// 메모리 임계 크기
	private static final int MAX_THRESHOLD 
			= 1024 * 1024 * 3;
	// 파일 1개당 최대 크기
	private static final long MAX_FILE_SIZE
	        = 1024 * 1024 * 40;
	// 요청파일 최대크기
	private static final long MAX_REQUEST_SIZE
	= 1024 * 1024 * 40;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// /WEB-INF/handler.properties
		String configFilePath = 
				config.getInitParameter("handler-config");
		
		Properties handlerProp = new Properties();
		
		// 설정파일을 읽어서 대응되는 핸들러객체를 생성하여 맵에 등록하기
		String configFileRealPath = 
				config.getServletContext()
					  .getRealPath(configFilePath);
		
		FileReader fr;
		try {
			fr = new FileReader(configFileRealPath);
			
			handlerProp.load(fr);
		}catch(IOException ex) {
			ex.printStackTrace();
			throw new ServletException(ex);
		}
		
		for(Object key : handlerProp.keySet()) {
			String reqUrl = (String) key;
			
			try {
				Class<?> klass = 
					Class.forName(handlerProp
							.getProperty(reqUrl));
				CommandHandler handlerInstance =
					(CommandHandler) klass.newInstance();
				cmdHandlerMap.put(reqUrl, handlerInstance);
			}catch(Exception ex) {
				ex.printStackTrace();
				throw new ServletException(ex);
			}
		}
		
		Set<Map.Entry<String, CommandHandler>> entrySet
			= cmdHandlerMap.entrySet();
		for(Map.Entry<String, CommandHandler> entry 
				: entrySet) {
			System.out.println(entry.getKey() 
					+ " => " + entry.getValue());
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req,resp);
	
	}
	
	private void process(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException  {
		
		// 요청 URL가져오기
		String reqURI = req.getRequestURI(); 
		
		// ContextPath 부분을 제거한 URL 가져오기
		if(reqURI.indexOf(req.getContextPath()) == 0) {
			reqURI = reqURI.substring(
					req.getContextPath().length());
		}
		
		System.out.println("reqURI : " + reqURI);
		System.out.println("cmdHandlerMap : " + cmdHandlerMap);
		
		CommandHandler handler = 
				cmdHandlerMap.get(reqURI);
		
		if(handler == null) {
			handler = new NullHandler();
		}
		
		String viewPage = ""; // 뷰화면 정보
		try {
			viewPage = handler.process(req, resp);
		}catch(Exception ex) {
			ex.printStackTrace();
			throw new ServletException(ex);
		}
		
		System.out.println("viewPage : " + viewPage);
		
		// VIEW 화면 처리
		if(viewPage != null) { // 뷰페이지가 존재하면..
			if(handler.isRedirect(req)) {
				resp.sendRedirect(viewPage);
			}else {
				RequestDispatcher dispatcher 
					= req.getRequestDispatcher(viewPage);
				dispatcher.forward(req, resp);
			}
			
		}
		
		
		
		
		
		
		
		
	}
	
		
		
		
	
}
