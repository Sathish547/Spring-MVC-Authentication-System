<%@page import="systech.cmr2spr.pg01b.LoginModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign-In</title>
<style>
<%@ include file="/ulib/css/css001.css" %>
</style>
</head>
<%
try {
	String msg=request.getAttribute("msg") + "";
	if(msg.equalsIgnoreCase("Create:Sign-up")) {
		out.println("<div style='color: green;'>");
		out.println("Successfully, <i>Register New Account</i>");
		out.println("</div>");
		request.removeAttribute("msg");
	}
	else if(msg.equalsIgnoreCase("Delete:Sign-up")) {
		LoginModel t=(LoginModel) request.getAttribute("elm");
		if(t == null) {
			return;
		}
		out.println("<div style='color: red;'>");
		out.println("Successfully, <i>Unregister following Account</i>");
		out.println("</div>");
		out.println("<div style='color: green'>User ID: ");
		out.println(t.getUid());
		out.println("</div>");
		out.println("<div style='color: blue'>User Password: ");
		out.println(t.getUpwd());
		out.println("</div>");
		request.removeAttribute("elm");
		request.removeAttribute("msg");
	} else if (msg.startsWith("err.:")) {
		out.println("<div style='color: red;'>");
		
		out.println("<i>" + msg.substring(5) + "</i>");
		out.println("</div>");
		request.removeAttribute("msg");
	}
} catch(Exception e) {
	out.println("err.: " + e.getMessage());
}
%>
<form action="pg01b.ctrl.module1" method="post" autocomplete="off">
<table>
<tr>
<td>User ID
<td>
<input
type="text"
id="tbxuid"
name="tbxuid"
placeholder="Enter exist user ID"
/>
<tr>
<td>User Password
<td>
<input
type="password" id="tbxupwd" name="tbxupwd"
placeholder="Enter exist user Password" />
<tr align="center">
<td colspan="2">
<input type="button" value="Cancel"
onclick="location.href='pg01b.ctrl.signin';"/>
<input type="button" value="Sign-up"
onclick="location.href='pg01b.ctrl.signup';"/>
<input type="submit" value="Sign-in"/>
</table>
</form>
</html>