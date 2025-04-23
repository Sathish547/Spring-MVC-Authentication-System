<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<style>
<%@ include file="/ulib/css/css001.css" %>
</style>
<script>
<%@ include file="/ulib/js/udate.js" %>
</script>
<script>
spn.innerHTML=uGetTime() + "<br><br>" + document.URL;</script></head>
<span id="spn"></span>
<br><br>
<a href="/prja/pg01b.ctrl.signin">Login</a>
<br><br>
<a href="/prja/pg01b.ctrl.signup">Register</a>
</html>