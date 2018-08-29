package authority.individual;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import authority.individual.DatabaseFactory;
import authority.individual.DataIndividual;

/**
 * Servlet implementation class LoginIndividual
 * @author: beastsam
 * @date: 2018.8.21
 * @Description:
 *  接收个人登陆页面发送用户名，密码，与数据库存储的用户名登录密码进行比对
 *  若密码正确，则生成session，10分钟失效（在web.xml配置）
 *  同时生成cookies，存储用户名，密码，24小时有效
 *  查询数据库用户权限名，权限编码，发送至individual_right_manage界面
 *  接收参数：name、password
 *  发送参数：identityId、rightId、rightName（存储在DataIndividual类的list中）
 *  跳转页面：individual_right_manage
 */

@WebServlet("/LoginIndividual")
public class LoginIndividual extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginIndividual() {
        super();
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
		//设置编码方式防止中文乱码
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		//接收index_individual页发送的用户名密码
		String name = request.getParameter("name");
        String webPassword = request.getParameter("password"); 

        try{
        	List<DataIndividual> listPw = new ArrayList<DataIndividual>();
        	DatabaseFactory dbControl = new DatabaseFactory();
        	listPw = dbControl.getPassword(name);
        	String identityId = null;
        	String resPassword = null;
        	 for (DataIndividual data : listPw) {
        		 identityId = data.getIdentityId();
        		 resPassword = data.getPassword();
        	 }
        	
			//逻辑判断，如果登录密码正确，则生成session，存储用户名，身份ID，并查询用户权限信息
			if(resPassword.equals(webPassword)) {	
				//生成session
				request.getSession().setAttribute("name", name);
				//生成cookies
			    Cookie cName = new Cookie("name", name);
			    Cookie cPassword = new Cookie("password", resPassword);
			    cName.setMaxAge(60*60*24);
			    cPassword.setMaxAge(60*60*24);
			    response.addCookie(cName);
			    response.addCookie(cPassword);
			    
			    List<DataIndividual> list = new ArrayList<DataIndividual>();			    
			    list = dbControl.getRightInfo(identityId);			    
	            request.setAttribute("list", list);
	            //跳转至individual_right_manage页面
				request.getRequestDispatcher("individual_right_manage.jsp").forward(request, response);
	        } else {
	            response.sendRedirect("index_individual.jsp");
			}	
        }            
        	catch (Exception e) { 
                e.printStackTrace(); 
            } 
	}
}


