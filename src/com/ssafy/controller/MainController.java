package com.ssafy.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/main.do")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// private LoginService loginService;
	// private GuestBookService guestBookService;

	@Override
	public void init() throws ServletException {
		super.init();
		// loginService = new LoginServiceImpl();
		// guestBookService = new GuestBookServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String root = request.getContextPath();
		
		String act = request.getParameter("act");
		
		if("mvlogin".equals(act)) {
			response.sendRedirect(root + "/user/login.jsp");
		} else if("mvjoin".equals(act)) {
			response.sendRedirect(root + "/user/join.jsp");
		} else if("login".equals(act)) {
			login(request, response);
		} else if("logout".equals(act)) {
			logout(request, response);
		} 
		
		/*
		else if("blahblah".equals(act)){
			doSomething(request,response);
		}
		else if("blahblah".equals(act)){
			response.sendRedirect(root + "/directory/filename.jsp");
		}
		*/
		
		/* 
		실거래가 영역
		*/
		
		/*
		회원관리 영역
		*/
		
		/*
		관심지역 영역
		*/
		
		/*
		공지사항 영역
		*/
		
		else {
			response.sendRedirect(root);
		}
	}
	
	/* 
	실거래가 영역
	*/
	
	/*
	회원관리 영역
	*/

	/*
	관심지역 영역
	*/
	
	/*
	공지사항 영역
	*/


	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("userinfo");
//		session.invalidate();
		response.sendRedirect(request.getContextPath());
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		String path = "/index.jsp";
		String userid = request.getParameter("userid");
		String userpwd = request.getParameter("userpwd");
		
		try {
			MemberDto memberDto = loginService.login(userid, userpwd);
			if(memberDto != null) {
//				session 설정
				HttpSession session = request.getSession();
				session.setAttribute("userinfo", memberDto);
				
//				cookie 설정
				String idsave = request.getParameter("idsave");
				if("saveok".equals(idsave)) {//아이디 저장을 체크 했다면.
					Cookie cookie = new Cookie("ssafy_id", userid);
					cookie.setPath(request.getContextPath());
					cookie.setMaxAge(60 * 60 * 24 * 365 * 40);//40년간 저장.
					response.addCookie(cookie);
				} else {//아이디 저장을 해제 했다면.
					Cookie cookies[] = request.getCookies();
					if(cookies != null) {
						for(Cookie cookie : cookies) {
							if("ssafy_id".equals(cookie.getName())) {
								cookie.setMaxAge(0);
								response.addCookie(cookie);
								break;
							}
						}
					}
				}
			} else {
				request.setAttribute("msg", "아이디 또는 비밀번호 확인 후 로그인해 주세요.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "로그인 중 문제가 발생했습니다.");
			path = "/error/error.jsp";
		}
		request.getRequestDispatcher(path).forward(request, response);
		*/
	}
	
}
