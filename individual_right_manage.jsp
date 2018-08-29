<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
<%@page import="authority.individual.DataIndividual"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
	@author:beastsam
	日期：2018.8
	功能：个人用户权限返回界面，接收LoginServlet返回的session以及用户现有权限信息（权限名，权限ID）
		   首先判断session状态，如果session值为空，则说明登录过期，重新跳转至登录界面
		   若session非空，则显示登录用户可删除的权限
		   用户还可以进入新权限申请界面
	传输方式：post
	接收参数：权限名称、权限ID
	发送参数：登录身份ID、删除权限编码、session
	数据接收服务器：DeleteIndividual
	跳转链接：新权限申请进度查询页面、主界面
--%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限管理界面</title>
<style type="text/css"> 
td { 
  font-size: 12px; 
} 
  
h2 { 
  margin: 0px 
} 
</style> 
</head>
<body> 
	
  <table align="center" width="450" border="1" height="180"
    bordercolor="white" bgcolor="black" cellpadding="1" cellspacing="1"> 
    <tr bgcolor="white"> 
      <td align="center" colspan="7"> 
        <h2>权限管理</h2> 
      </td> 
    </tr>  
    <tr align="center" bgcolor="#e1ffc1"> 
      <td><b>序号</b></td>  
      <td><b>权限名称</b></td>
      <td><b>权限ID</b></td> 
      <td><b>操作</b></td> 
    </tr> 
    
    <%!String identityId = null;%> 
    <%
     	// 获取权限信息集合 
           List<DataIndividual> list = (List<DataIndividual>) request.getAttribute("list"); 
           // 判断集合是否有效 
           if (list == null || list.size() < 1) { 
             out.print("没有数据！"); 
           } else { 
             // 遍历权限集合中的数据 
             for (DataIndividual data : list) {
     %> 
     <%!int i = 0;%> 
    <tr align="center" bgcolor="white"> 
      <td><%out.println(++i); %></td>
      <td><%=data.getRightName()%></td> 
	  <td><%=data.getRightId()%></td> 
	  <% identityId = data.getIdentityId();%>
      <td> 
      <a href="DeleteIndividual?identity_ID=<%=data.getIdentityId()%>&right_ID=<%=data.getRightId()%>">删除</a> 
      </td>   
    </tr> 
    <% 
      } 
      } 
    %> 
  </table> 
  <h2 align="center"> 
    <a href="ApplyQueryIndividual?identityId=<%=identityId%>">权限申请</a> 
  </h2>
    <h2 align="center"> 
    <a href="QueryRateIndividual?identityId=<%=identityId%>">进度查询</a> 
  </h2>
  <h2 align="center"> 
    <a href="CookieVerify">返回主界面</a> 
  </h2> 
	
  
</body> 
</html>
