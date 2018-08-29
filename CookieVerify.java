package authority.individual;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import authority.individual.DatabaseFactory;

/**
 * Servlet implementation class CookieVerify
 * @author: beastsam
 * @date: 2018.8.21
 * @Description:
 *  获取用户cookie，提取其中的身份ID与密码，与数据库进行比对，若密码匹配则直接跳转至权限管理页面，
 *  若密码不匹配则跳转至登录页面
 *  接收参数：cookie(identityId、password)
 *  发送参数：identityId、rightId、rightName（存储在DataIndividual类的list中）
 *  跳转页面：individual_right_manage.jsp
 *  		  index_individual.jsp
 */

@WebServlet("/CookieVerify")
public class CookieVerify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookieVerify() {
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
		String cName = null;
		String cPassword = null;
		Cookie[] cookies = request.getCookies();
		
		/**
		 * 判断cookies是否为空，若非空则提取用户名密码，调用DatabaseFactory的getPw类，获取数据库密码并匹配
		 * 若cookies为空，则登录失效，跳转至登录界面
		 */
		
		if (cookies != null) {		
			for (int i = 0; i<cookies.length; i++) {
				if (cookies[i].getName().equals("name")) {
					cName = cookies[i].getValue();
				}
				if (cookies[i].getName().equals("password")) {
					cPassword = cookies[i].getValue();
				}
			}

			try {
				List<DataIndividual> listPw = new ArrayList<DataIndividual>();
	        	DatabaseFactory dbControl = new DatabaseFactory();
				listPw = dbControl.getPassword(cName);
				String identityId = null;
	        	String rPassword = null;
	        	 for (DataIndividual data : listPw) {
	        		 identityId = data.getIdentityId();
	        		 rPassword = data.getPassword();
	        	 }
	        	//密码匹配
				if(cPassword.equals(rPassword)) {
		        	List<DataIndividual> list = new ArrayList<DataIndividual>();			    
				    list = dbControl.getRightInfo(identityId);	
		            request.setAttribute("list", list);
		            //跳转至individual_right_manage页面
					request.getRequestDispatcher("individual_right_manage.jsp").forward(request, response);
				}else {
					response.sendRedirect("index_individual.jsp");
				} 
			    }catch(Exception e) {
					e.printStackTrace();
				}
			}else {
				response.sendRedirect("index_individual.jsp");
			}
	}
}
					
