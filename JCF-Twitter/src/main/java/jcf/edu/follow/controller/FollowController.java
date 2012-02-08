package jcf.edu.follow.controller;

import jcf.edu.follow.service.FollowService;
import jcf.edu.login.util.SessionUtil;
import jcf.edu.user.model.UserVO;
import jcf.sua.mvc.MciRequest;
import jcf.sua.mvc.MciResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FollowController {

	@Autowired
	private FollowService followService;

	@RequestMapping("/follow")
	public void follow(MciRequest request, MciResponse response) {
		UserVO currentUser = SessionUtil.getCurrentUser();
		followService.insertFollow(currentUser.getUserId(), request.getParam("id"));
		response.setViewName("redirect:/tweet");

	}

	@RequestMapping("/unFollow")
	public void unfollow(MciRequest request, MciResponse response) {
		UserVO currentUser = SessionUtil.getCurrentUser();
		followService.deleteFollow(currentUser.getUserId(), request.getParam("id"));
		response.setViewName("redirect:/tweet");
	}
}
