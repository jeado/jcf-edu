package jcf.edu.user.model;

public class UserVO {

	private String userId;
	private String userName;
	private String userEmail;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	@Override
	public boolean equals(Object obj) {
		UserVO compareToObj = (UserVO) obj;
		if(this.getUserId().equals(compareToObj.getUserId())){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", " + "userName=" + userName
				+ ", userRole=" + userEmail + "]";
	}

}
