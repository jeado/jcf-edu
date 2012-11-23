package jcf.edu.user.fileHandler;


import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import jcf.edu.user.model.UserPicDTO;
import jcf.edu.user.service.UserService;
import jcf.upload.FileInfo;
import jcf.upload.MultiPartInfo;
import jcf.upload.handler.UploadEventHandler;
import jcf.upload.persistence.PersistenceManager;

import org.springframework.beans.factory.annotation.Autowired;

public class UserPicUploadEventHandler implements UploadEventHandler {

	@Autowired
	private UserService userService;
	
	public long getMaxUploadSize() {
		return 10000000;
	}

	public String getFolder(HttpServletRequest request) {
		return "userpic";
	}

	public void postprocess(String folder, MultiPartInfo info,
			PersistenceManager persistenceManager) {
		UserPicDTO picInfo = userService.findPicInfo((String) info.getAttributes().get("userId"));
		
		FileInfo fileInfo = info.getFileInfos().get(0);
		
		UserPicDTO userPicDTO = new UserPicDTO();
		userPicDTO.setFileName(fileInfo.getName());
		userPicDTO.setFilePath(folder);
		userPicDTO.setFileUuid(fileInfo.getCallName());
		userPicDTO.setUserId((String) info.getAttributes().get("userId"));
		
		if(picInfo == null){
			userService.inserPicInfo(userPicDTO);
		}else{
			userService.updatePicInfo(userPicDTO);
		}
	}

	public String createFileNameIfAccepted(String folder, FileInfo fileInfo) {
		return UUID.randomUUID().toString();
	}

	public void prepareStorage(PersistenceManager persistenceManager,
			String folder) {
	}

}
