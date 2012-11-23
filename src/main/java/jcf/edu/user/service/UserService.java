package jcf.edu.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jcf.edu.login.util.SessionUtil;
import jcf.edu.user.model.UserPicDTO;
import jcf.edu.user.model.UserVO;
import jcf.query.core.QueryExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private QueryExecutor queryExecutor;

	public UserVO findUser(String userId) {
		return queryExecutor.queryForObject("user.findUser", userId, UserVO.class);
	}
	
	public List<UserVO> findUserList(String id, String name) {
		
		Map<String,String> searchMap = new HashMap<String,String>();
		searchMap.put("id", id);
		searchMap.put("name", name);
		
		List<UserVO>userList = queryExecutor.queryForList("user.selectUser", searchMap, UserVO.class);
		
		return userList;
	}
	
	public List<UserVO> findUserListWithoutCurrentUserList() {

		List<UserVO>userList = queryExecutor.queryForList("user.selectUser", null, UserVO.class);
		userList.remove(SessionUtil.getCurrentUser());
		return userList;
	}

	public int insertUser(UserVO uservo) {
		return queryExecutor.update("user.insertUser", uservo);
	}

	public void updateUser(UserVO uservo) {
		queryExecutor.update("user.updateUser", uservo);
	}

	public void deleteUser(UserVO uservo) {
		queryExecutor.update("user.deleteUser", uservo);
	}

	public UserVO info(String id) {
		Map<String,String> searchMap = new HashMap<String,String>();
		searchMap.put("id", id);
		List<UserVO> userList = queryExecutor.queryForList("user.selectUser",
				searchMap, UserVO.class);
		UserVO user =userList.get(0);
		return user;
	}

	public void inserPicInfo(UserPicDTO userPicDTO) {
		queryExecutor.update("user.insertPic", userPicDTO);
	}

	public UserPicDTO findPicInfo(String userId) {
		Map<String,String> searchMap = new HashMap<String,String>();
		searchMap.put("id", userId);
		List<UserPicDTO> picList = queryExecutor.queryForList("user.selectPicInfo",
				searchMap, UserPicDTO.class);
		if(picList.isEmpty()){
			return null;
		}else{
			return picList.get(0);
		}
	}

	public void updatePicInfo(UserPicDTO userPicDTO) {
		queryExecutor.update("user.updatePic", userPicDTO);
	}

}
