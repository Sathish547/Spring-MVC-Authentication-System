<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign-Up</title>
<style>
<%@ include file="/ulib/css/css001.css" %>"
</style>
</head>
<%
try {
	String msg=request.getAttribute("msg") + "";
	if(msg.startsWith("err.:")) {
		out.println("<div style='color: red;'>");
		out.println("<i>" + msg.substring(5) + "</i>");
		out.println("</div>");
		request.removeAttribute("msg");
	}
} catch(Exception e) {
	out.println("err.: " + e.getMessage());
}
%>
<form action="pg01b.ctrl.adduser" method="post" autocomplete="off">
<table>
<tr>
<td>User ID
<td>
<input type="text" id="tbxuid" name="tbxuid" placeholder="Enter new user ID"/>
<tr>
<td>User Password
<td>
<input type="password" id="tbxupwd" name="tbxupwd" placeholder="Enter new user Password"/>
<tr align="center">
<td colspan="2">
<input type="button" value="Cancel" onclick="location.href='pg01b.ctrl.signup';"/>
<input type="button" value="sign-in" onclick="location.href='pg01b.ctrl.signin';"/>
<input type="submit" value="Sign-up"/>
</table>
</form>
</html>