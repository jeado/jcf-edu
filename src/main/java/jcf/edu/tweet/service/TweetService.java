package jcf.edu.tweet.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jcf.edu.follow.model.UserFollowingDTO;
import jcf.edu.tweet.model.TweetDTO;
import jcf.query.core.QueryExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TweetService {
	
	@Autowired
	private QueryExecutor queryExecutor;

	public List<TweetDTO> selectMyContents(String id) {
		Map<String,String> searchMap = new HashMap<String,String>();
		searchMap.put("id", id);
		List<UserFollowingDTO> followList = queryExecutor.queryForList(
				"user.selectFollow", searchMap, UserFollowingDTO.class);
		List<TweetDTO> followerContentList = new ArrayList<TweetDTO>();
		for (int i = 0; i < followList.size(); i++) {
			Map<String,String> searchMap2 = new HashMap<String,String>();
			UserFollowingDTO follow = followList.get(i);
			searchMap2.put("register", follow.getFollowingId());
			List<TweetDTO> contentList = queryExecutor.queryForList(
					"tweet.selectContent", searchMap2, TweetDTO.class);
			for (int j = 0; j < contentList.size(); j++) {
				TweetDTO content = contentList.get(j);
				followerContentList.add(content);
			}
		}
		Map<String,String> searchMap3 = new HashMap<String,String>();
		searchMap3.put("register", id);
		List<TweetDTO> myContentList = queryExecutor.queryForList(
				"tweet.selectContent", searchMap3, TweetDTO.class);
		for (int j = 0; j < myContentList.size(); j++) {
			TweetDTO content = myContentList.get(j);
			followerContentList.add(content);
		}
		Collections.sort(followerContentList);
		return followerContentList;
	}

	/**
	 * @param content
	 */
	public void insertContent(TweetDTO content) {
		queryExecutor.update("tweet.insertContent", content);		
	}
	
	/**
	 * @param id
	 */
	public void deleteContent(int id) {
		queryExecutor.update("tweet.deleteContent", id);
	}

}
