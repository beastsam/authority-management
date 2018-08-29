<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
	@author:beastsam
	@date: 2018.8.21
	功能：资料上传
	隐藏参数：identityId、rightId
--%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限资料上传</title>
</head>
<body>
<% String rightName = request.getParameter("right_name"); %>
<h1><%out.println(rightName);%>:资料上传</h1>
<form method="post" action="UploadIndividual" enctype="multipart/form-data">
    选择一个文件:
    <input type="file" name="uploadFile" />
     <br/>
    <input type="hidden" name="identityId" value=<%=request.getParameter("identity_ID")%>  />
    <input type="hidden" name="rightId" value=<%= request.getParameter("right_ID") %>  />
    <br/><br/>
    <input type="submit" value="上传" />
</form>
</body>
</html>
