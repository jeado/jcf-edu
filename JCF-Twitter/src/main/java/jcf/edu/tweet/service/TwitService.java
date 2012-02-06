package jcf.edu.tweet.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jcf.edu.file.model.ContentFileDTO;
import jcf.edu.tweet.model.TweetDTO;
import jcf.edu.user.model.UserFollowingDTO;
import jcf.query.core.QueryExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwitService {
	@Autowired
	private QueryExecutor queryExecutor;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public List selectMyContents(String id) {
		Map searchMap = new HashMap();
		searchMap.put("id", id);
		List<UserFollowingDTO> followList = queryExecutor.queryForList(
				"user.selectFollow", searchMap, UserFollowingDTO.class);
		List followerContentList = new ArrayList();
		for (int i = 0; i < followList.size(); i++) {
			Map searchMap2 = new HashMap();
			UserFollowingDTO follow = followList.get(i);
			searchMap2.put("register", follow.getFollowingId());
			List<TweetDTO> contentList = queryExecutor.queryForList(
					"user.selectContent", searchMap2, TweetDTO.class);
			for (int j = 0; j < contentList.size(); j++) {
				TweetDTO content = contentList.get(j);
				followerContentList.add(content);
			}
		}
		Map searchMap3 = new HashMap();
		searchMap3.put("register", id);
		List<TweetDTO> myContentList = queryExecutor.queryForList(
				"user.selectContent", searchMap3, TweetDTO.class);
		for (int j = 0; j < myContentList.size(); j++) {
			TweetDTO content = myContentList.get(j);
			followerContentList.add(content);
		}
		Collections.sort(followerContentList);
		return followerContentList;
	}

	public void insertContent(TweetDTO content, String uuid, String name) {
		try {
			queryExecutor.update("user.insertContent", content);
			// 콘텐츠 삽입
			Map searchMap = new HashMap();
			List<TweetDTO> contentList = queryExecutor.queryForList(
					"user.selectContent", searchMap, TweetDTO.class);
			if (name.trim().isEmpty() == false) {
				// 전체 콘텐츠 목록 불러오기-쿼리문 order by id(시퀀스)
				TweetDTO contentTemp = contentList
						.get(contentList.size() - 1);
				// 시퀀스넘버가 가장 큰 콘텐츠. 즉 가장 최근에 입력한 콘텐츠
				ContentFileDTO contentFile = new ContentFileDTO();
				contentFile.setContentsId(contentTemp.getId());
				// 방금 입력한 콘텐츠의 id를 콘텐츠파일의 contentsId로 set한다.
				contentFile.setFileUuid(uuid);
				queryExecutor.update("user.insertContentFile", contentFile);
				// 콘텐츠파일 삽입
			}
		} catch (Exception e) {
			return;
		}
	}

	public void deleteContent(int id) {
		queryExecutor.update("user.deleteContent", id);
		queryExecutor.update("user.deleteContentFile", id);
	}

	public List selectFile(int id) {
		Map searchMap = new HashMap();
		searchMap.put("id", id);
		List<ContentFileDTO> contentFile = queryExecutor.queryForList(
				"user.selectContentFile", searchMap, ContentFileDTO.class);
		return contentFile;
	}

	public List selectFile2() {
		Map searchMap = new HashMap();
		List<ContentFileDTO> contentFile = queryExecutor.queryForList(
				"user.selectContentFile", searchMap, ContentFileDTO.class);
		return contentFile;
	}

}
