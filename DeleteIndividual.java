package authority.individual;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import authority.individual.DatabaseFactory;
import authority.individual.DataIndividual;

import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class DeleteIndividual
 * @author 高杨
 * 日期：2018.8.21
 * 功能：实现个人用户权限删除功能，接收individual_right_manage发送过来的身份ID，权限编码
 * 		 通过判断session是否为空判断用户登录状态，若为空则登录过期，跳转至登录页面
 * 		 若session不为空，则执行删除操作，之后再次查询用户拥有权限结果并返回给individual_right_manage界面
 * 接收参数：identity_ID、right_ID
 * 返回参数：List<DataIndividual> list（包含删除操作后用户拥有的identityId、Right_Name、Right_ID）
 * 跳转页面：individual_right_manage
 */

@WebServlet("/DeleteIndividual")
public class DeleteIndividual extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteIndividual() {
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
 		String identityId = request.getParameter("identity_ID");
 		String rightId = request.getParameter("right_ID");	
 		
 		try {
 			DatabaseFactory dbControl = new DatabaseFactory();
 			dbControl.delRight(identityId,rightId);
 			List<DataIndividual> list = new ArrayList<DataIndividual>();	
 			//数据库工厂类删除权限方法
		    list = dbControl.getRightInfo(identityId);			    
            request.setAttribute("list", list);
            request.getRequestDispatcher("/individual_right_manage.jsp").forward(request, response);
 		} catch(Exception e){
 			 e.printStackTrace(); 
 		}	
	}

}
