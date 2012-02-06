package jcf.edu.user.model;

public class UserVO {

	private String userId;
	private String password;
	private String userName;
	private String userRole;

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String name) {
		this.userName = name;
	}

	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", password=" + password
				+ ", userName=" + userName + ", userRole=" + userRole + "]";
	}

}
