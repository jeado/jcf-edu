package jcf.edu.file.controller;

import jcf.edu.user.model.UserPicDTO;
import jcf.edu.user.service.UserService;
import jcf.sua.mvc.MciRequest;
import jcf.sua.mvc.MciResponse;
import jcf.upload.FileInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FileController {

	@Autowired
	private UserService userService;

	@RequestMapping("/file/fileView/{userId}")
	public void fileView(MciRequest request, MciResponse response, @PathVariable String userId) {
		UserPicDTO pic = userService.findPicInfo(userId);
		response.setDownloadFile(new FileInfo(pic.getFilePath(), pic.getFileUuid()));
	}
}
