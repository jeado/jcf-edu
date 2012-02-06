package jcf.edu.user.controller;

import java.util.ArrayList;
import java.util.List;

import jcf.edu.user.model.UserAuthoritiesDTO;
import jcf.edu.user.model.UserFollowingDTO;
import jcf.edu.user.model.UserRoleDTO;
import jcf.edu.user.model.UserVO;
import jcf.edu.user.service.FollowService;
import jcf.edu.user.service.UserService;
import jcf.sua.mvc.MciRequest;
import jcf.sua.mvc.MciResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private FollowService followService;

	@RequestMapping("/user/create")
	public void test(MciRequest request, MciResponse response) {
		// 파라미터를 (HttpServletRequest request)로 받지않고
		// 내부에서 추상화를 해서 어떤 클라이언트 요청이든 받을수 있도록 하였음.
		// jcfsua서블릿
		List<UserRoleDTO> userRole = userService.role();
		response.setList("userRole", userRole);
		response.setViewName("/user/userReg");
	}

	@RequestMapping("/user/findUsers")
	public void findUser(MciRequest request, MciResponse response) {
		Authentication currentUser = SecurityContextHolder.getContext()
				.getAuthentication();
		String currentId = currentUser.getName().toString();

		String id = request.getParam("userId");
		String name = request.getParam("userName");
		List<UserVO> userVoList = userService.findUserList(id, name);

		List<UserVO> realUserList = new ArrayList<UserVO>();
		for (int i = 0; i < userVoList.size(); i++) {
			UserVO user = userVoList.get(i);
			if (user.getUserId().equals(currentId)) {
			} else
				realUserList.add(user);
		}

		List<UserFollowingDTO> followList = userService.followList(currentId);
		response.setList("userList", realUserList);
		response.setList("myFollowing", followList);
		response.setViewName("userList");

	}

	@RequestMapping("/user/insertUser")
	public void insertUser(MciRequest request, MciResponse response) {
		UserVO user = request.getParam(UserVO.class);
		userService.insert(user);
		this.updateUser(request, response);
		response.setViewName("redirect:/user/findUsers.action");
	}

	@RequestMapping("/user/info")
	public void info(MciRequest request, MciResponse response) {
		String id = request.getParam("userId");
		UserVO userVo = userService.info(id);
		UserAuthoritiesDTO role = userService.findRole(id);
		if (role == null) {
			userVo.setUserRole("");
		}
		userVo.setUserRole(role.getAuthority());
		response.set("user", userVo);

		List<UserRoleDTO> userRole = userService.role();
		response.setList("userRole", userRole);

		response.setViewName("/user/userReg");
	}

	public void updateUser(MciRequest request, MciResponse response) {
		UserVO user = request.getParam(UserVO.class);
		userService.update(user);
	}

	@RequestMapping("user/deleteUser")
	public void deleteUser(MciRequest request, MciResponse response) {
		UserVO user = request.getParam(UserVO.class);
		userService.delete(user);
		response.setViewName("redirect:/user/findUsers.action");
	}

	@RequestMapping("/user/follow")
	public void follow(MciRequest request, MciResponse response) {
		Authentication currentUser = SecurityContextHolder.getContext()
				.getAuthentication();
		String currentId = currentUser.getName().toString();
		String id = request.getParam("id");
		followService.insertFollow(currentId, id);
		response.setViewName("redirect:findUsers.action");

	}

	@RequestMapping("/user/unFollow")
	public void unfollow(MciRequest request, MciResponse response) {
		Authentication currentUser = SecurityContextHolder.getContext()
				.getAuthentication();
		String currentId = currentUser.getName().toString();
		String id = request.getParam("id");

		followService.deleteFollow(currentId, id);
		response.setViewName("redirect:findUsers.action");

	}

}
