<%@ page import="com.springbook.biz.board.BoardVO" %>
<%@ page import="com.springbook.biz.board.impl.BoardDAO" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    String seq = request.getParameter("seq");

    BoardVO vo = new BoardVO();
    vo.setSeq(Integer.parseInt(seq));

    new BoardDAO().deleteBoard(vo);
    response.sendRedirect("getBoardList.jsp");
%>