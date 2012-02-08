package jcf.edu.follow.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jcf.edu.follow.model.UserFollowingDTO;
import jcf.query.core.QueryExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowService {
	
	@Autowired
	private QueryExecutor queryExecutor;
	
	public List<UserFollowingDTO> findFollowList(String currentId) {
		Map<String,String> searchMap = new HashMap<String,String>();
		searchMap.put("id", currentId);
		List<UserFollowingDTO> followList = queryExecutor.queryForList("user.selectFollow",
				searchMap, UserFollowingDTO.class);
		return followList;
	}
	
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
