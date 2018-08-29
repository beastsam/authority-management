<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
<%@page import="authority.individual.DataIndividual"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
	@author:beastsam
	@date: 2018.8.21
	@Description:
	功能：权限申请页面
--%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限申请</title>
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
   <%
 		//从session里把identityId拿出来，并赋值给userName 
 		String name = (String)request.getSession().getAttribute("name"); 
 		//判断session是否过期，若过期则跳转至登录界面
 		if(name == null){
 			response.sendRedirect("index_individual.jsp");
 		}
 	%>	
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
    <%!int i = 0;%> 
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
    <tr align="center" bgcolor="white"> 
      <td><%out.println(++i); %></td>
      <td><%=data.getRightName()%></td> 
	  <td><%=data.getRightId()%></td> 
      <td> 
      <a href="individual_file_upload.jsp?identity_ID=<%=data.getIdentityId()%>
      &right_ID=<%=data.getRightId()%>
      &right_name=<%=data.getRightName() %>">申请</a> 
      </td>   
    </tr> 
    <% 
      } 
      } 
    %> 
  </table> 
  
  <h1 align="center"> 
    <a href="index_individual.jsp">返回主界面</a> 
  </h1> 
  
    <h1 align="center"> 
    <a href="CookieVerify">返回主界面</a> 
  </h1> 


</body>
</html>
