package authority.individual;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import authority.individual.DatabaseFactory;
import authority.individual.DataIndividual;
/**
 * Servlet implementation class ApplyQueryIndividual
 * @author 高杨
 * 日期：2018.8.23
 * 功能：接收individual_right_manage页面发送的用户id，比对数据库，查询符合用户身份
 * 		 等级的可操作的权限，将查询结果返回至individual_right_apply页面
 * 接收参数：identityId
 * 发送参数：DataIndividual list(包含identityId,rightId,rightName)
 * 接收页面：individual_right_apply
 * 
 */
@WebServlet("/ApplyQueryIndividual")
public class ApplyQueryIndividual extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplyQueryIndividual() {
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
		//session过期判断
 		String name = (String)request.getSession().getAttribute("name"); 
 		//判断session是否过期，若过期则跳转至登录界面
 		if(name == null){
 			response.sendRedirect("index_individual.jsp");
 		}
		String identityId = request.getParameter("identityId");
		 try{
			 List<DataIndividual> list = new ArrayList<DataIndividual>();
			 DatabaseFactory dbControl = new DatabaseFactory();
			 list = dbControl.getApplyRight(identityId);
	         request.setAttribute("list", list); 
	         request.setAttribute("identityId", identityId);
	         request.getRequestDispatcher("/individual_right_apply.jsp").forward(request, response);
	     }catch (Exception e) { 
	           e.printStackTrace(); 
	    } 
	}

}
