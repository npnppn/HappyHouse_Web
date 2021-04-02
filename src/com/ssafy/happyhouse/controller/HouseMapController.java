package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ssafy.happyhouse.model.ArticleDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.service.ArticleService;
import com.ssafy.happyhouse.model.service.ArticleServiceImpl;
import com.ssafy.happyhouse.model.service.HouseMapServiceImpl;

@WebServlet("/map")
public class HouseMapController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ArticleService articleService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		articleService = new ArticleServiceImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String root = request.getContextPath();
		response.setCharacterEncoding("UTF-8");
		String act = request.getParameter("act");
		if("sido".equals(act)) {
			PrintWriter out = response.getWriter();
			List<SidoGugunCodeDto> list = null;
			JSONArray arr = new JSONArray();
			try {
				list = HouseMapServiceImpl.getHouseMapService().getSido();
				for(SidoGugunCodeDto dto : list) {
					JSONObject obj = new JSONObject();
					obj.put("sido_code", dto.getSidoCode());
					obj.put("sido_name", dto.getSidoName());
					arr.add(obj);
				}
			} catch (Exception e) {
				arr = new JSONArray();
				JSONObject obj = new JSONObject();
				obj.put("message_code", "-1");
				arr.add(obj);
				e.printStackTrace();
			} finally {
				out.print(arr.toJSONString());
				out.close();
			}
		}//sido
		else if("gugun".equals(act)) {
			String sido = request.getParameter("sido");
			PrintWriter out = response.getWriter();
			List<SidoGugunCodeDto> list = null;
			JSONArray arr = new JSONArray();
			try {
				list = HouseMapServiceImpl.getHouseMapService().getGugunInSido(sido);
				for(SidoGugunCodeDto dto : list) {
					JSONObject obj = new JSONObject();
					obj.put("gugun_code", dto.getGugunCode());
					obj.put("gugun_name", dto.getGugunName());
					arr.add(obj);
				}
			} catch (Exception e) {
				arr = new JSONArray();
				JSONObject obj = new JSONObject();
				obj.put("message_code", "-1");
				arr.add(obj);
				e.printStackTrace();
			} finally {
				out.print(arr.toJSONString());
				out.close();
			}
		}//gugun
		else if("dong".equals(act)) {
			String gugun = request.getParameter("gugun");
			PrintWriter out = response.getWriter();
			List<HouseInfoDto> list = null;
			JSONArray arr = new JSONArray();
			try {
				list = HouseMapServiceImpl.getHouseMapService().getDongInGugun(gugun);
				for(HouseInfoDto dto : list) {
					JSONObject obj = new JSONObject();
					obj.put("code", dto.getCode());
					obj.put("dong", dto.getDong());
					arr.add(obj);
				}
			} catch (Exception e) {
				arr = new JSONArray();
				JSONObject obj = new JSONObject();
				obj.put("message_code", "-1");
				arr.add(obj);
				e.printStackTrace();
			} finally {
				out.print(arr.toJSONString());
				out.close();
			}
		}//dong
		else if("apt".equals(act)) {
			String dong = request.getParameter("dong");
			PrintWriter out = response.getWriter();
			List<HouseInfoDto> list = null;
			JSONArray arr = new JSONArray();
			try {
				list = HouseMapServiceImpl.getHouseMapService().getAptInDong(dong);
				for(HouseInfoDto dto : list) {
					JSONObject obj = new JSONObject();
					obj.put("no", dto.getNo());
					obj.put("dong", dto.getDong());
					obj.put("aptName", dto.getAptName());
					obj.put("code", dto.getCode());
					obj.put("jibun", dto.getJibun());
					obj.put("lat", dto.getLat());
					obj.put("lng", dto.getLng());
					arr.add(obj);
				}
			} catch (Exception e) {
				arr = new JSONArray();
				JSONObject obj = new JSONObject();
				obj.put("message_code", "-1");
				arr.add(obj);
				e.printStackTrace();
			} finally {
				out.print(arr.toJSONString());
				out.close();
			}
		}//dong
		
		else if("mvwrite".equals(act)) {
			response.sendRedirect(root + "/article/write.jsp");
		} else if("write".equals(act)) {
			writeArticle(request, response);
		} else if("list".equals(act)) {
			listArticle(request, response);
		} else if("mvarticle".equals(act)) {
			moveModifyArticle(request, response);
		} else if("mvmodify".equals(act)) {
			moveModifyArticle(request, response);
		} else if("modify".equals(act)) {
			modifyArticle(request, response);
		} else if("delete".equals(act)) {
			deleteArticle(request, response);
		}
		else {
			response.sendRedirect(root);
		}
	}//process
	
	
	private void deleteArticle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = "/index.jsp";
		int articleno = Integer.parseInt(request.getParameter("articleno"));

		try {
			articleService.deleteArticle(articleno);
			path = "/map?act=list&key=&word=";
			response.sendRedirect(request.getContextPath() + path);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "글삭제 처리 중 문제가 발생했습니다.");
			path = "/error/error.jsp";
			request.getRequestDispatcher(path).forward(request, response);
		}
	}

	private void modifyArticle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = "/index.jsp";
		ArticleDto articleDto = new ArticleDto();
		articleDto.setArticleno(Integer.parseInt(request.getParameter("articleno")));
		articleDto.setSubject(request.getParameter("subject"));
		articleDto.setContent(request.getParameter("content"));

		try {
			articleService.modifyArticle(articleDto);
			path = "/map?act=list&key=&word=";
			response.sendRedirect(request.getContextPath() + path);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "글작성중 문제가 발생했습니다.");
			path = "/error/error.jsp";
			request.getRequestDispatcher(path).forward(request, response);
		}
	}

	private void moveModifyArticle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = "/index.jsp";
		int articleno = Integer.parseInt(request.getParameter("articleno"));
		System.out.println(articleno);
		try {
			ArticleDto articleDto = articleService.getArticle(articleno);
			request.setAttribute("article", articleDto);
			path = "/article/modify.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "글수정 처리 중 문제가 발생했습니다.");
			path = "/error/error.jsp";
		}
		request.getRequestDispatcher(path).forward(request, response);
		System.out.println(path);
	}

	private void listArticle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = "/index.jsp";
		String key = request.getParameter("key");
		String word = request.getParameter("word");
		try {
			List<ArticleDto> list = articleService.listArticle(key, word);
			request.setAttribute("articles", list);
			path = "/article/list.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "글목록을 얻어오는 중 문제가 발생했습니다.");
			path = "/error/error.jsp";
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

	private void writeArticle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = "/index.jsp";
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		ArticleDto aritcleDto = new ArticleDto();
		aritcleDto.setUserid(memberDto.getUserid());
		aritcleDto.setUserid("ssafy");
		aritcleDto.setSubject(request.getParameter("subject"));
		aritcleDto.setContent(request.getParameter("content"));

		try {
			articleService.writeArticle(aritcleDto);
			path = "/article/writesuccess.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "글작성중 문제가 발생했습니다.");
			path = "/error/error.jsp";
		}
		/*if (memberDto != null) {
			ArticleDto guestBookDto = new ArticleDto();
			guestBookDto.setUserid(memberDto.getUserid());
			guestBookDto.setSubject(request.getParameter("subject"));
			guestBookDto.setContent(request.getParameter("content"));

			try {
				articleService.writeArticle(guestBookDto);
				path = "/article/writesuccess.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "글작성중 문제가 발생했습니다.");
				path = "/error/error.jsp";
			}
		} else {
			request.setAttribute("msg", "로그인 후 사용 가능한 페이지입니다.");
			path = "/error/error.jsp";
		}*/
		request.getRequestDispatcher(path).forward(request, response);
	}

}