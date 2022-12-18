package com.springbook.biz.view.controller;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;
import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
/*
public class DispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private HandlerMapping handlerMapping;
    private ViewResolver viewResolver;

    @Override
    public void init() throws ServletException {
        System.out.println("init 호출");
        handlerMapping = new HandlerMapping();
        viewResolver = new ViewResolver();
        viewResolver.setPrefix("./");
        viewResolver.setSuffix(".jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet 호출");
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        System.out.println("doPost 호출");
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();
        String path = uri.substring(uri.lastIndexOf("/"));

        Controller ctr = handlerMapping.getController(path);
        String viewName = ctr.handleRequest(request, response);

        String view = null;
        if (!viewName.contains("do")) view = viewResolver.getView(viewName);
        else view = viewName;

        response.sendRedirect(view);
    }

    /*
    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1. 클라이언트 요청 path 정보 추출
        String uri = request.getRequestURI();
        String path = uri.substring(uri.lastIndexOf("/"));
        System.out.println(path);

        // 2. 클라이언트 요청 path에 따른 분기처리
        if (path.equals("/login.do")) {
            System.out.println("로그인 처리");

            String id = request.getParameter("id");
            String password = request.getParameter("password");

            UserVO vo = new UserVO();
            vo.setId(id);
            vo.setPassword(password);

            UserVO user = new UserDAO().getUser(vo);
            if (user != null) response.sendRedirect("getBoardList.do");
            else response.sendRedirect("login.jsp");
        }

        else if (path.equals("/logout.do")) {
            System.out.println("로그아웃 처리");

            request.getSession().invalidate();
            response.sendRedirect("login.jsp");
        }

        else if (path.equals("/insertBoard.do")) {
            System.out.println("글 등록 처리");

            String title = request.getParameter("title");
            String writer = request.getParameter("writer");
            String content = request.getParameter("content");

            BoardVO vo = new BoardVO();
            vo.setTitle(title);
            vo.setWriter(writer);
            vo.setContent(content);

            BoardDAO boardDAO = new BoardDAO();
            boardDAO.insertBoard(vo);

            response.sendRedirect("getBoardList.do");
        }

        else if (path.equals("/updateBoard.do")) {
            System.out.println("글 수정 처리");

            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String seq = request.getParameter("seq");

            BoardVO vo = new BoardVO();
            vo.setTitle(title);
            vo.setContent(content);
            vo.setSeq(Integer.parseInt(seq));

            new BoardDAO().updateBoard(vo);
            response.sendRedirect("getBoardList.do");
        }

        else if (path.equals("/deleteBoard.do")) {
            System.out.println("글 삭제 처리");

            String seq = request.getParameter("seq");

            BoardVO vo = new BoardVO();
            vo.setSeq(Integer.parseInt(seq));

            new BoardDAO().deleteBoard(vo);
            response.sendRedirect("getBoardList.do");
        }

        else if (path.equals("/getBoard.do")) {
            System.out.println("글 상세 조회 처리");

            String seq = request.getParameter("seq");

            BoardVO vo = new BoardVO();
            vo.setSeq(Integer.parseInt(seq));

            BoardDAO boardDAO = new BoardDAO();
            BoardVO board = boardDAO.getBoard(vo);

            HttpSession session = request.getSession();
            session.setAttribute("board", board);
            response.sendRedirect("getBoard.jsp");
        }

        else if (path.equals("/getBoardList.do")) {
            System.out.println("글 목록 검색 처리");

            BoardDAO boardDAO = new BoardDAO();
            List<BoardVO> boardList = boardDAO.getBoardList();

            // 검색 결과를 세션에 저장하고 목록 화면으로 이동
            HttpSession session = request.getSession();
            session.setAttribute("boardList", boardList);
            response.sendRedirect("getBoardList.jsp");
        }
    }
    */
//}
