package jcf.edu.login.util;

import jcf.edu.user.model.UserVO;
import jcf.sua.mvc.MciRequestContextHolder;

public class SessionUtil {

	public static void addUser(UserVO userVO){
		MciRequestContextHolder.get().getHttpServletRequest().getSession().setAttribute("LoginUser", userVO);
	}
	
	public static UserVO getCurrentUser() {
		
		UserVO currentUser = (UserVO) MciRequestContextHolder.get().getHttpServletRequest().getSession().getAttribute("LoginUser");
		
		if (currentUser == null) throw new RuntimeException("로그인이 정상적으로 이루어 지지 않았습니다.");
		
		return currentUser;
	}
}
