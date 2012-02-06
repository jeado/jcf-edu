package jcf.edu.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jcf.edu.file.model.ContentFileDTO;
import jcf.edu.user.model.UserAuthoritiesDTO;
import jcf.edu.user.model.UserFollowingDTO;
import jcf.edu.user.model.UserPicDTO;
import jcf.edu.user.model.UserRoleDTO;
import jcf.edu.user.model.UserVO;
import jcf.query.core.QueryExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private QueryExecutor queryExecutor;

	public List findUserList(String id, String name) {
		Map searchMap = new HashMap();
		searchMap.put("id", id);
		searchMap.put("name", name);
		List userList = queryExecutor.queryForList("user.selectUser",
				searchMap, UserVO.class);

		// List<UserVO> userList2 = new ArrayList<UserVO>();
		// // 유저 테이블과 권한테이블을 매칭시켜준다.
		// for (int i = 0; i < userList.size(); i++) {
		// UserVO user = (UserVO) userList.get(i);
		// UserAuthoritiesDTO role = this.findRole(user.getUserId());
		// user.setUserRole(role.getAuthority());
		// userList2.add(user);
		// }
		return userList;
	}

	public List followList(String currentId) {
		Map searchMap = new HashMap();
		searchMap.put("id", currentId);
		List followList = queryExecutor.queryForList("user.selectFollow",
				searchMap, UserFollowingDTO.class);
		return followList;

	}

	public List role() {
		Map searchMap = new HashMap();
		List userList = queryExecutor.queryForList("user.selectRoles",
				searchMap, UserRoleDTO.class);
		return userList;
	}

	public UserAuthoritiesDTO findRole(String id) {
		Map searchMap = new HashMap();
		searchMap.put("id", id);
		List userList = queryExecutor.queryForList("user.selectAuthorities",
				searchMap, UserAuthoritiesDTO.class);

		UserAuthoritiesDTO tmp = new UserAuthoritiesDTO();
		tmp.setAuthority("");
		tmp.setUserId("");
		userList.add(tmp);
		UserAuthoritiesDTO role = (UserAuthoritiesDTO) userList.get(0);
		return role;
	}

	public int insert(UserVO uservo) {
		int i;
		try {
			i = queryExecutor.update("user.insertUser", uservo);
			UserAuthoritiesDTO au = new UserAuthoritiesDTO();
			au.setUserId(uservo.getUserId());
			au.setAuthority(uservo.getUserRole());
			queryExecutor.update("user.insertAuthorities", au);
		} catch (Exception e) {
			return 0;
		}
		return i;
	}

	public void update(UserVO uservo) {
		try {
			queryExecutor.update("user.updateUser", uservo);

			UserAuthoritiesDTO au = new UserAuthoritiesDTO();
			au.setUserId(uservo.getUserId());
			au.setAuthority(uservo.getUserRole());
			queryExecutor.update("user.updateAuthorities", au);
		} catch (Exception e) {
			return;
		}
	}

	public void delete(UserVO uservo) {
		queryExecutor.update("user.deleteUser", uservo);
	}

	public UserVO info(String id) {
		Map searchMap = new HashMap();
		searchMap.put("id", id);
		List userList = queryExecutor.queryForList("user.selectUser",
				searchMap, UserVO.class);
		UserVO user = (UserVO) userList.get(0);
		return user;
	}

	public int picInsert(UserPicDTO pic) {
		int i;
		try {
			i = queryExecutor.update("user.insertPic", pic);
		} catch (Exception e) {
			return 0;
		}
		return i;
	}

	public UserPicDTO picInfo(String id) {
		Map searchMap = new HashMap();
		searchMap.put("id", id);
		List picList = queryExecutor.queryForList("user.selectPicInfo",
				searchMap, UserPicDTO.class);
		UserPicDTO pic = (UserPicDTO) picList.get(0);
		return pic;
	}

	public UserPicDTO picInfo2(int id) {
		Map searchMap = new HashMap();
		searchMap.put("id", id);
		List<ContentFileDTO> contentFile = queryExecutor.queryForList(
				"user.selectContentFile", searchMap, ContentFileDTO.class);

		ContentFileDTO temp = new ContentFileDTO();
		temp = contentFile.get(0);
		Map searchMap2 = new HashMap();
		searchMap2.put("fileUuid", temp.getFileUuid());
		List picList = queryExecutor.queryForList("user.selectPicInfo",
				searchMap2, UserPicDTO.class);
		if (picList == null) {
			return null;
		}
		UserPicDTO pic = (UserPicDTO) picList.get(0);
		return pic;
	}

	public int picUpdate(UserPicDTO pic) {
		int i;
		try {
			i = queryExecutor.update("user.updatePic", pic);
		} catch (Exception e) {
			return 0;
		}
		return i;
	}
}
