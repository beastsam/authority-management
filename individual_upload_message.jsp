<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
	@author:beastsam
	@date: 2018.8.21
	@Description:
	功能：申请资料上传结果显示界面
--%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传结果</title>
</head>
<body>
	<center>
        <h2>${message}</h2>
    </center>
    
    <p>进度查询号：<%out.println(request.getAttribute("rateId")); %></p>
    
    <h1 align="center"> 
    <a href="CookieVerify">返回主界面</a> 
  </h1> 
</body>
</html>
