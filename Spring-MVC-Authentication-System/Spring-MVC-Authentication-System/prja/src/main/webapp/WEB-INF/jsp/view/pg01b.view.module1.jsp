<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Module-1: User Account</title>
<style>
<%@ include file="/ulib/css/css001.css" %>
</style>
<script>
function tbxupwd_onclick() {
tbxupwdx.value="";
tbxuid.value="";
tbxupwdn.value="";
}
</script>
</head>
<%
try {
	String msg=request.getAttribute("msg") + "";
	if(msg.equalsIgnoreCase("Update: Password")) {
		out.println("<div style='color: green;'>");
		out.println("Successfully, <i>" + msg + "</i>");
		out.println("</div>");
		request.removeAttribute("msg");
	}else if (msg.startsWith("err.:")) {
		out.println("<div style='color: red;'>");
		out.println("<i>" + msg.substring(5) + "</i>");
		out.println("</div>");
		request.removeAttribute("msg");
	}
} catch(Exception e) {
	out.println("err.: " + e.getMessage());
}
%>
<form id="frmmodule1" action="pg0lb.ctrl.updateuser" method="post" autocomplete="off">
<input type="hidden" id="hdnuid" name="hdnuid" value="<%=request.getAttribute("uid") %>" />
<input type="hidden" id="hdnupwd" name="hdnupwd" value="<%=request.getAttribute("upwd") %>" />
<table>
<tr>
<td>User ID
<td>
<input type="text" id="tbxuid" name="tbxuid"
placeholder="Enter exist user ID" value="<%=request.getAttribute("uid")%>" 
readonly="readonly" />
<tr>
<td>User Old Password
<td>
<input type="password" id="tbxupwdx" name="tbxupwdx"
placeholder="Enter old user password" />
<tr>
<td>User New Password
<td>
<input type="password" id="tbxupwdn" name="tbxupwdn"
placeholder="Enter new user password" />
<tr>
<td>
<input type="button" onclick="tbxupwd_onclick();" 
value="Cancel" />
<td>
<input type="submit" formaction="pg01b.ctrl.home" 
value="Home" />
<td>
<input type="submit" formaction="pg0lb.ctrl.deluser" 
value="Delete Account" />
<td>
<input type="submit" formaction="pg01b.ctrl.signout" 
value="Sign-out" />
<td>
<a href="javascript: frmmodule1.submit();">Change Password</a>
</table>
</form>
</html>