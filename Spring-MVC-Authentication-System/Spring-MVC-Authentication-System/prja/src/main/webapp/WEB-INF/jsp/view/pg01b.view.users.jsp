<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Info</title>
<style>
<%@ include file="/ulib/css/css001.css" %>
</style>
</head>
<% 
String stb1=request.getAttribute("atbUsers") + "";
out.println("<h1 style='color: red;'>Users Info</h1>");
out.println("<br><br>");
out.println(stb1);
request.removeAttribute("atbUsers");
%>
<br><br>
<a href="/prja/pg01a.ctrl.home">Home</a>
</html>