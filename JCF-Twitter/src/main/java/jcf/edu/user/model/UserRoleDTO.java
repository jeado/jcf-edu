package jcf.edu.user.model;

public class UserRoleDTO {

	private String authority;
	private String roleName;

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "UserRoleDTO [authority=" + authority + ", roleName=" + roleName
				+ "]";
	}
}
