package jcf.edu.user.service;

import jcf.edu.user.model.UserFollowingDTO;
import jcf.query.core.QueryExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowService {
	@Autowired
	private QueryExecutor queryExecutor;

	public void insertFollow(String currentUserId, String followTargetId) {
		UserFollowingDTO follow = new UserFollowingDTO();
		follow.setFollowingId(followTargetId);
		follow.setUserId(currentUserId);
		queryExecutor.update("user.insertFollow", follow);

	}

	public void deleteFollow(String currentUserId, String followTargetId) {
		UserFollowingDTO follow = new UserFollowingDTO();
		follow.setFollowingId(followTargetId);
		follow.setUserId(currentUserId);
		queryExecutor.update("user.deleteFollow", follow);
	}
}
