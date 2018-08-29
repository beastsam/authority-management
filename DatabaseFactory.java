package authority.individual;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authority.individual.DataIndividual;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author： beastsam
 * 日期：2018.8.15
 * 功能：数据库操作工厂类 
 * 配置文件：database.properties（修改数据库地址、登录名、密码）
 */

public class DatabaseFactory { 
  //获取配置文件信息  
  private ResourceBundle bundle = ResourceBundle.getBundle("database");
  private String driverClassName = bundle.getString("driverClass");
  private String url = bundle.getString("dbUrl");
  private String userName = bundle.getString("dbUser");
  private String password = bundle.getString("dbPwd");
  
  private static DatabaseFactory databaseFactory=null; 
  private Connection conn = null;
  private PreparedStatement ps = null;
  private ResultSet rs = null;
  
  /**
   * author  beastsam
   * 日期：2018.8.28
   * 功能：数据库连接
   */
  public DatabaseFactory() { 
    try { 
      Class.forName(driverClassName); 
    } catch (ClassNotFoundException e) { 
      e.printStackTrace(); 
    } 
  } 
    
  public Connection getConnection() throws SQLException 
  { 
    return DriverManager.getConnection(url, userName, password);   
  } 
  
  public static DatabaseFactory getInstance() 
  {   
    if (null==databaseFactory) { 
      databaseFactory=new DatabaseFactory(); 
    } 
    return databaseFactory; 
      
  } 
  
  /**
   * @author  beastsam
   * 日期：2018.8.28
   * 功能：查询数据库，返回用户权限名，权限ID
   * @param String identityId:用户身份ID
   * @return list:用户权限查询结果，包含identityId(String)，Right_Name(String)，Right_ID(String)
   */
  public List<DataIndividual> getRightInfo(String identityId ) {
	  //生成Data类list，用于存储用户权限信息
	  List<DataIndividual> list = new ArrayList<DataIndividual>(); 
	  try {
		  conn = DatabaseFactory.getInstance().getConnection(); 
		  String sql = "SELECT db_rightcode.Right_Name, db_idright.Right_ID "
				+ "FROM db_idright, db_rightcode "
				+ "WHERE db_idright.Right_ID =  db_rightcode.Right_ID and db_idright.Identity_ID=?";	  
		  ps = conn.prepareStatement(sql);
		  ps.setString(1, identityId);
		  rs = ps.executeQuery();
		  while (rs.next()) { 
			  DataIndividual dataIndividual = new DataIndividual(); 
			  dataIndividual.setIdentityId(identityId); 
			  dataIndividual.setRightName(rs.getString("Right_Name")); 
			  dataIndividual.setRightId(rs.getString("Right_ID")); 
			  list.add(dataIndividual); 
		  }  
		  rs.close();
          ps.close();
          conn.close();
	  }catch (SQLException e){
		  e.printStackTrace();
	  }catch(Exception e){
          // 处理 Class.forName 错误
		  e.printStackTrace();
	  }finally{
          // 关闭资源
          try{
              if(ps!=null) ps.close();
          }catch(SQLException se2){
          }// 什么都不做
          try{
              if(conn!=null) conn.close();
          }catch(SQLException se){
              se.printStackTrace();
          }
      }
	  return list;
  }
  
  /**
   * @author  beastsam
   * 日期：2018.8.28
   * 功能：查询数据库，返回用户身份ID，登录密码
   * @param name(String):用户名
   * @return list:用户登录密码，身份ID查询，包含identityId(String)，password(String)
   */
  public List<DataIndividual> getPassword(String name) {
	  List<DataIndividual> list = new ArrayList<DataIndividual>(); 
	  try{
			//数据库连接
			conn = DatabaseFactory.getInstance().getConnection(); 
			//查询数据库存储的密码、身份ID
			String sql = "SELECT Password, Identity_ID FROM db_idinfo WHERE User_name = ?";
			//通过网页参数补全查询语句
			ps = conn.prepareStatement(sql); 
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			//存储数据库密码查询结果
			while(rs.next()) {
				DataIndividual dataIndividual = new DataIndividual(); 
				dataIndividual.setIdentityId(rs.getString("Identity_ID")); 
				dataIndividual.setPassword(rs.getString("Password"));
				list.add(dataIndividual); 
			}
			rs.close();
	        ps.close();
	        conn.close();
	  }catch (SQLException e){
		  e.printStackTrace();
	  }catch(Exception e){
          // 处理 Class.forName 错误
		  e.printStackTrace();
	  }finally{
          // 关闭资源
          try{
              if(ps!=null) ps.close();
          }catch(SQLException se2){
          }// 什么都不做
          try{
              if(conn!=null) conn.close();
          }catch(SQLException se){
              se.printStackTrace();
          }
      }
	  return list; 
  }
  
  /**
   * @author  beastsam
   * 日期：2018.8.28
   * 功能：删除权限
   * @param identityId(String):用户名 rightId(String)权限编码
   * @return true:删除成功
   * 		 false:删除失败
   */
  public boolean delRight(String identityId, String rightId) {
	  try{
			//数据库连接
			conn = DatabaseFactory.getInstance().getConnection(); 
			//查询数据库存储的密码、身份ID
			String sql = "DELETE FROM db_idright WHERE Identity_ID = ? AND Right_ID = ?";
			//通过网页参数补全查询语句
			ps = conn.prepareStatement(sql); 
			ps.setString(1, identityId);
			ps.setString(2, rightId);
			ps.executeUpdate();
	        ps.close();
	        conn.close();
	  }catch (SQLException e){
		  e.printStackTrace();
		  return false;
	  }catch(Exception e){
          // 处理 Class.forName 错误
		  e.printStackTrace();
		  return false;
	  }finally{
          // 关闭资源
          try{
              if(ps!=null) ps.close();
          }catch(SQLException se2){
          }// 什么都不做
          try{
              if(conn!=null) conn.close();
          }catch(SQLException se){
              se.printStackTrace();
          }
      }
	  return true; 
  }
  
  /**
   * @author  beastsam
   * 日期：2018.8.28
   * 功能：查询数据库，返回用户可申请的权限名，权限id
   * @param identityId(String):身份ID
   * @return list:身份ID，新权限名、新权限编码
   */
  public List<DataIndividual> getApplyRight(String identityId) {
	  List<DataIndividual> list = new ArrayList<DataIndividual>(); 
	  try{
			//数据库连接
			conn = DatabaseFactory.getInstance().getConnection(); 
			//查询数据库存储的密码、身份ID
			String sql = "SELECT db_rc.Right_ID, db_rc.Right_Name FROM db_rightcode db_rc \r\n" + 
					"WHERE  db_rc.Right_ID NOT IN (SELECT Right_ID FROM db_idright where Identity_ID = ?) \r\n" + 
					"AND db_rc.Min_Level <= (SELECT Manage_Level FROM db_idinfo WHERE Identity_ID = ?)";
			//通过网页参数补全查询语句
			ps = conn.prepareStatement(sql); 
			ps.setString(1, identityId);
			ps.setString(2, identityId);
			ResultSet rs = ps.executeQuery();
			//存储数据库密码查询结果
			while(rs.next()) {
				 DataIndividual dataIndividual = new DataIndividual(); 
	              dataIndividual.setIdentityId(identityId); 
	              dataIndividual.setRightName(rs.getString("Right_Name")); 
	              dataIndividual.setRightId(rs.getString("Right_ID")); 
	              list.add(dataIndividual); 
			}
			rs.close();
	        ps.close();
	        conn.close();
	  }catch (SQLException e){
		  e.printStackTrace();
	  }catch(Exception e){
          // 处理 Class.forName 错误
		  e.printStackTrace();
	  }finally{
          // 关闭资源
          try{
              if(ps!=null) ps.close();
          }catch(SQLException se2){
          }// 什么都不做
          try{
              if(conn!=null) conn.close();
          }catch(SQLException se){
              se.printStackTrace();
          }
      }
	  return list; 
  }
  
  /**
   * @author  beastsam
   * 日期：2018.8.28
   * 功能：进度插入
   * @param identityId(String):用户名 rightId(String)权限编码
   * @return true:插入成功
   * 		 false:插入失败
   */
  public boolean insertRate(String identityId, String rateId) {
	  try{
			//数据库连接
			conn = DatabaseFactory.getInstance().getConnection(); 
			//查询数据库存储的密码、身份ID
			String sql = "INSERT INTO db_applyrate(Identity_ID, Rate_Status, Rate_ID) VALUES(?,0,?)";
			//通过网页参数补全查询语句
			ps = conn.prepareStatement(sql); 
			ps.setString(1, identityId);
			ps.setString(2, rateId);
			ps.executeUpdate();
	        ps.close();
	        conn.close();
	  }catch (SQLException e){
		  e.printStackTrace();
		  return false;
	  }catch(Exception e){
          // 处理 Class.forName 错误
		  e.printStackTrace();
		  return false;
	  }finally{
          // 关闭资源
          try{
              if(ps!=null) ps.close();
          }catch(SQLException se2){
        	  return false;
          }
          try{
              if(conn!=null) conn.close();
          }catch(SQLException se){
              se.printStackTrace();
              return false;
          }
      }
	  return true; 
  }
  
  /**
   * @author  beastsam
   * 日期：2018.8.28
   * 功能：查询数据库，权限申请进度查询
   * String identityId:用户身份ID
   * @return list:用户权限查询结果，包含identityId(String)，Rate_Status(String)，Rate_ID(String)
   */
  public List<DataIndividual> getRateInfo(String identityId ) {
	  //生成Data类list，用于存储用户权限信息
	  List<DataIndividual> list = new ArrayList<DataIndividual>(); 
	  try {
		  conn = DatabaseFactory.getInstance().getConnection(); 
		  String sql = "SELECT Rate_Status, Rate_ID FROM Db_ApplyRate WHERE Identity_ID = ?";
		  ps = conn.prepareStatement(sql);
		  ps.setString(1, identityId);
		  rs = ps.executeQuery();
		  while (rs.next()) { 
			  DataIndividual dataIndividual = new DataIndividual(); 
              dataIndividual.setRateStatus(rs.getString("Rate_Status")); 
              dataIndividual.setIdentityId(identityId);
              dataIndividual.setRateId(rs.getString("Rate_ID"));
              list.add(dataIndividual); 
		  }  
		  rs.close();
          ps.close();
          conn.close();
	  }catch (SQLException e){
		  e.printStackTrace();
	  }catch(Exception e){
          // 处理 Class.forName 错误
		  e.printStackTrace();
	  }finally{
          // 关闭资源
          try{
              if(ps!=null) ps.close();
          }catch(SQLException se2){
          }// 什么都不做
          try{
              if(conn!=null) conn.close();
          }catch(SQLException se){
              se.printStackTrace();
          }
      }
	  return list;
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  


} 
