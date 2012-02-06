package jcf.edu.user.model;

public class UserFollowingDTO {

	private String userId;
	private String followingId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFollowingId() {
		return followingId;
	}

	public void setFollowingId(String followingId) {
		this.followingId = followingId;
	}

	@Override
	public String toString() {
		return "UserFollowingDTO [userId=" + userId + ", followingId="
				+ followingId + "]";
	}

}
