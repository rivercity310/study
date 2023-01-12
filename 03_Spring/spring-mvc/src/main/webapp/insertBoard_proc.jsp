<%@ page import="com.springbook.biz.mvc.board.BoardVO" %>
<%@ page import="com.springbook.biz.mvc.board.impl.BoardDAO" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<%
    request.setCharacterEncoding("UTF-8");
    String title = request.getParameter("title");
    String writer = request.getParameter("writer");
    String content = request.getParameter("content");

    BoardVO vo = new BoardVO();
    vo.setTitle(title);
    vo.setWriter(writer);
    vo.setContent(content);

    new BoardDAO().insertBoard(vo);
    response.sendRedirect("getBoardList.jsp");
%>