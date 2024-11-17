<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>

<%@ page import="org.example.hisnet.SubjectDAO" %>
<%@ page import="org.example.hisnet.SubjectVO" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="javax.servlet.http.HttpServletResponse" %>
<%@ page import="java.io.IOException" %>
<%

  SubjectDAO dao = new SubjectDAO();

  int id = Integer.parseInt(request.getParameter("id"));
  String category = request.getParameter("category");
  String code = request.getParameter("code");
  String name = request.getParameter("name");
  int englishRatio = Integer.parseInt(request.getParameter("englishRatio"));
  int credits = Integer.parseInt(request.getParameter("credits"));
  String classNum = request.getParameter("classNum");
  String professor = request.getParameter("professor");
  String classTime = request.getParameter("classTime");
  String classRoom = request.getParameter("classRoom");
  String type = request.getParameter("type");

  SubjectVO subject = new SubjectVO();
  subject.setId(id);
  subject.setCategory(category);
  subject.setCode(code);
  subject.setName(name);
  subject.setEnglishRatio(englishRatio);
  subject.setCredits(credits);
  subject.setClassNum(classNum);
  subject.setProfessor(professor);
  subject.setClassTime(classTime);
  subject.setClassRoom(classRoom);
  subject.setGrade(type);

  int result = dao.update(subject);

  // 결과 페이지로 리다이렉트
  if (result > 0) {
    response.sendRedirect("list.jsp");
  } else {
    out.println("<h3>수정 실패. 다시 시도해주세요.</h3>");
  }
%>
