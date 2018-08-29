<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%> 
<%@page import="authority.individual.DataIndividual"%> 
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
<title>进度查询结果</title>
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
        <h2>进度状态</h2> 
      </td> 
    </tr> 
    <tr align="center" bgcolor="#e1ffc1"> 
      <td><b>序号</b></td> 
      <td><b>状态编码</b></td> 
      <td><b>状态信息</b></td> 
    </tr> 
    <%!int i = 0;%> 
    <%
     	// 获取状态信息集合 
           List<DataIndividual> list = (List<DataIndividual>) request.getAttribute("list"); 
           // 判断集合是否有效 
           if (list == null || list.size() < 1) { 
             out.print("没有数据！"); 
           } else { 
           // 遍历集合中的数据 
             for (DataIndividual data : list) {
     %> 
    <tr align="center" bgcolor="white"> 
      <td><%out.println(++i); %></td>
      <td><%=data.getRateId()%></td> 
    <% 
    switch(data.getRateStatus()) {
    case "0":
    	 %>	
       <td>正在审核 </td>
       <% 
       break;
    case "1":%>	
       <td>审核通过</td>
        <%break;
    case "2":%>
       <td>审核拒绝</td>
       <% break;
    default:%>
       <td>系统错误</td>
      <%  break;
    }
    %>
    <% 
      } 
      } 
    %> 
  </table> 
  <h2 align="center"> 
    <a href="index.jsp">返回权限查询界面</a> 
  </h2> 
  
</body>
</html>
