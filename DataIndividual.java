package authority.individual;

/**
 * @author： beastsam
 * 日期：2018.8
 * 功能：数据操作，获取数据以及存储数据
 * 参数：identityId  身份ID
 * 		 rightId     权限编码
 * 		 rightName   权限名称
 * 		 deviceId    设备编码
 * 		 rateId      权限申请进度号
 * 		 rateStatus  进度状态
 */	

public class DataIndividual {
	  // 身份编号 
	  private String identityId; 
	  // 权限编码
	  private String rightId; 
	  // 权限名称
	  private String rightName; 
	  // 设备编码
	  private int deviceId;
	  //进度号
	  private String rateId;
	  //进度状态
	  private String rateStatus;
	  //登录密码
	  private String password;
	  
	  public String getIdentityId() { 
		    return identityId; 
		  } 
	  public void setIdentityId(String id) { 
		    this.identityId = id; 
		  } 

	  public String getRightName() { 
		    return rightName; 
		  } 
	  public void setRightName(String name) { 
		    this.rightName = name; 
		  } 

	 public String getRightId() {
		 return rightId;
	 }
	 public void setRightId(String rightId) {
		 this.rightId = rightId;
	 }
	  
	  public int getDeviceId() { 
		    return deviceId; 
		  } 
	  public void setDeviceId(int deviceId) { 
		    this.deviceId = deviceId; 
		  } 
	  
	  public String getRateId() { 
		    return rateId; 
		  } 
	  public void setRateId(String rateId) { 
		    this.rateId = rateId; 
		  } 
	  
	  public String getRateStatus() { 
		    return rateStatus; 
		  } 
	  public void setRateStatus(String rateStatus) { 
		    this.rateStatus = rateStatus; 
		  } 
	  
	  public String getPassword() { 
		    return password; 
		  } 
	  public void setPassword(String password) { 
		    this.password = password; 
		  } 
	  
	  
}
