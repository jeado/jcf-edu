package jcf.edu.login.conroller;

import jcf.edu.login.util.SessionUtil;
import jcf.edu.user.model.UserVO;
import jcf.edu.user.service.UserService;
import jcf.sua.mvc.MciRequest;
import jcf.sua.mvc.MciResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@Autowired 
	private UserService service;

	@RequestMapping(value="/login")
	public void loginPage(MciRequest mciRequest, MciResponse mciResponse){
		mciResponse.setViewName("login");
	}
	
	@RequestMapping(value="/loginHandle")
	public void loginHandle(MciRequest mciRequest, MciResponse mciResponse){
		String userId = mciRequest.getParam("userId");
		UserVO findedUser = service.findUser(userId);
		if(findedUser == null){
			throw new RuntimeException("사용자를 찾을 수 없습니다.");
		}else{
			SessionUtil.addUser(findedUser);
			mciResponse.setViewName("redirect:/tweet");
		}
	}
}
