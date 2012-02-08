package jcf.edu.tweet.controller;

import java.util.List;

import jcf.edu.follow.model.UserFollowingDTO;
import jcf.edu.follow.service.FollowService;
import jcf.edu.login.util.SessionUtil;
import jcf.edu.tweet.model.TweetDTO;
import jcf.edu.tweet.service.TweetService;
import jcf.edu.user.model.UserVO;
import jcf.edu.user.service.UserService;
import jcf.sua.mvc.MciRequest;
import jcf.sua.mvc.MciResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TweetController {

	@Autowired
	private TweetService tweetService;

	@Autowired
	private FollowService followService;
	
	@Autowired
	private UserService userService;

	@RequestMapping("/tweet")
	public void listTwitter(MciRequest request, MciResponse response) {
		UserVO currentUser = SessionUtil.getCurrentUser();
		List<TweetDTO> content = tweetService.selectMyContents(currentUser.getUserId());
		List<UserVO> userList = userService.findUserListWithoutCurrentUserList();
		List<UserFollowingDTO> followingList = followService.findFollowList(currentUser.getUserId());

		response.set("currentUser",currentUser);
		response.setList("tweetList", content);
		response.setList("userList", userList);
		response.setList("followingList", followingList);
		
		response.setViewName("twitter");
	}
	
	@RequestMapping("/tweet/{userId}")
	public void listTwitter(MciRequest request, MciResponse response, @PathVariable String userId) {
		UserVO currentUser = SessionUtil.getCurrentUser();
		List<TweetDTO> content = tweetService.selectMyContents(userId);
		List<UserVO> userList = userService.findUserListWithoutCurrentUserList();
		List<UserFollowingDTO> followingList = followService.findFollowList(currentUser.getUserId());
		
		response.set("currentUser",currentUser);
		response.setList("tweetList", content);
		response.setList("userList", userList);
		response.setList("followingList", followingList);
		
		response.setViewName("twitter");
	}

	@RequestMapping("/tweet/insert")
	public void insertTwitter(MciRequest request, MciResponse response){
		UserVO currentUser = SessionUtil.getCurrentUser();
		TweetDTO content = new TweetDTO(currentUser.getUserId(), request.getParam("tweets"));
		tweetService.insertContent(content);
		response.setViewName("redirect:/tweet");
	}

	@RequestMapping("/tweet/delete")
	public void deleteTwitter(MciRequest request, MciResponse response) {
		int id = Integer.parseInt(request.getParam("id"));
		tweetService.deleteContent(id);
		response.setViewName("redirect:/tweet");
	}

}
