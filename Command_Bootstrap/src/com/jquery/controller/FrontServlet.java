package com.jquery.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jquery.handler.CommandHandler;


public class FrontServlet extends HttpServlet {
	
	private HandlerMapper handlerMapper;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		String handlerMapperType = config.getInitParameter("handlerMapper");

		try {
			this.handlerMapper = (HandlerMapper) injectionBean(handlerMapperType);
			System.out.println("[FrontServlet] handlerMapper 가 준비되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[FrontServlet] handlerMapper 가 준비되지 않았습니다.");
		}
	}
	
	private Object injectionBean(String classType) throws Exception {
		Class<?> cls = Class.forName(classType);
		return cls.newInstance();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request,response);
	}
	
	private void requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 사용자 URI 검출
		String command = request.getRequestURI(); // contextPath 포함.

		if (command.indexOf(request.getContextPath()) == 0) { // contextPath 삭제
			command = command.substring(request.getContextPath().length());
		}
		
		//commandHandler 실행 (HandlerMapper 의뢰 handler 할당)
		CommandHandler handler = null;
		String view = null;
		
		
		
		if (handlerMapper != null){
			handler = handlerMapper.getHandler(command);
			if(handler!=null) { //올바른 요청
				
				try {
					view = handler.process(request, response);
					
					if(view !=null) ViewResolver.view(request, response, view);
					
				} catch (Exception e) {
					e.printStackTrace();
					response.setContentType("text/html;charset=utf-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('서비스 장애가 발생했습니다.\n잠시 후에 이용바랍니다.');");
					out.println("history.go(-1);");
					out.println("</script>");
				}
				
			}else { //잘못된 요청
				System.out.println("!! not found : " + command);
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('잘못된 요청입니다. 똑바로 하세요.');");
				out.println("history.go(-1);");
				out.println("</script>");
			}
		}else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('서비스 장애가 발생했습니다.\n잠시 후에 이용바랍니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
		}
	}

}





