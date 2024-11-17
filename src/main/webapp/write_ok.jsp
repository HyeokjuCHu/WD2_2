<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="org.example.hisnet.SubjectDAO" %>
<%@ page import="org.example.hisnet.SubjectVO" %>

<%
    SubjectVO vo = new SubjectVO();
    vo.setCategory(request.getParameter("category"));
    vo.setCode(request.getParameter("code"));
    vo.setName(request.getParameter("name"));

    String englishRatioStr = request.getParameter("englishRatio");
    String creditsStr = request.getParameter("credits");

    try {
        vo.setEnglishRatio(Integer.parseInt(englishRatioStr));
    } catch (NumberFormatException e) {
        out.println("<h3>영어 비율은 숫자여야 합니다.</h3>");
        return; // 처리 중단
    }

    try {
        vo.setCredits(Integer.parseInt(creditsStr));
    } catch (NumberFormatException e) {
        out.println("<h3>학점은 숫자여야 합니다.</h3>");
        return; // 처리 중단
    }

    vo.setClassNum(request.getParameter("classNum"));
    vo.setProfessor(request.getParameter("professor"));
    vo.setClassTime(request.getParameter("classTime"));
    vo.setClassRoom(request.getParameter("classRoom"));
    vo.setGrade(request.getParameter("type"));

    SubjectDAO dao = new SubjectDAO();
    dao.add(vo); // add 메서드 호출

    response.sendRedirect("list.jsp");
%>
