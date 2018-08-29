<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
	@author:beastsam
	日期：2018.8
	功能：权限管理个人登陆主页，输入用户名密码其中数据库连接本地数据库dzpj
	已有用户名与密码详见数据库结构设计文档
	传输方式：post
	发送参数：用户名（支持中文）、密码
	数据接收服务器：LoginServlet
--%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人登录界面</title>
</head>
<body>

<form action="LoginIndividual" method="POST">
用户名: <input type="text" name="name">
<br />
密码: <input type="text" name="password" />
<br />
<input type="submit" value="提交" />
</form>

<a href="index.jsp">返回主页面</a> 

</body>
</html>
