package jcf.edu.user.model;

public class UserAuthoritiesDTO {
	private String userId;
	private String authority;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "UserAuthoritiesDTO [userId=" + userId + ", authority="
				+ authority + "]";
	}

}
