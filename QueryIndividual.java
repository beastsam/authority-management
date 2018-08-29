package authority.individual;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList; 
import java.util.List; 

import authority.individual.DatabaseFactory;
import authority.individual.DataIndividual;

/**
 * Servlet implementation class QueryIndividual
 * @author beastsam
 * 开始日期：2018.8.16
 * 功能：实现个人用户进度查询
 * 输入参数：identityId(String)
 * 发送参数：identityId、rightId、rightName（存储在DataIndividual类的list中）
 * 跳转页面：individual_rate_result
 */
@WebServlet("/QueryRateIndividual")
public class QueryRateIndividual extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryRateIndividual() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//从session里把identityId拿出来，并赋值给userName 
 		String name = (String)request.getSession().getAttribute("name"); 
 		//判断session是否过期，若过期则跳转至登录界面
 		if(name == null){
 			response.sendRedirect("index_individual.jsp");
 		}
 		String identityId = request.getParameter("identityId");
		
		try{
			List<DataIndividual> list = new ArrayList<DataIndividual>();
        	DatabaseFactory dbControl = new DatabaseFactory();
			list = dbControl.getRateInfo(identityId);
            request.setAttribute("list", list); 
          } catch (Exception e) { 
            e.printStackTrace(); 
          }            
          request.getRequestDispatcher("individual_rate_result.jsp").forward(request, response); 
	}
}
