package jcf.edu.file.controller;

import java.util.List;

import jcf.edu.file.model.ContentFileDTO;
import jcf.edu.tweet.service.TwitService;
import jcf.edu.user.model.UserPicDTO;
import jcf.edu.user.service.UserService;
import jcf.sua.mvc.MciRequest;
import jcf.sua.mvc.MciResponse;
import jcf.upload.FileInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class FileSampleController {

	@Autowired
	private UserService userService;

	@Autowired
	private TwitService contentsService;

	@RequestMapping("/createFileForm")
	public void createFileForm(MciRequest request, MciResponse response) {
		response.setViewName("/file/fileSample");
	}

	@RequestMapping(value="/upload/file/**/*",  method=RequestMethod.POST)
	public void upload(MciRequest request, MciResponse response){
		
		
		/*
		Authentication currentUser = SecurityContextHolder.getContext()
				.getAuthentication();
		String userId = currentUser.getName().toString();
		String subDirectory = request.getParam("urn");
		List<FileInfo> list = request.getAttachmenets();
		if (list.size() == 1) {
			FileInfo fileInfo = list.get(0);
			FileItem fileItem = (FileItem) fileInfo.getModel().get(
					fileInfo.getFieldName());
			String uuid = UUID.randomUUID().toString();
			persistenceManager.save(fileInfo, subDirectory, uuid,
					fileItem.getInputStream());
			UserPicDTO pic = new UserPicDTO();
			pic.setUserId(userId);
			pic.setFileName(fileInfo.getName());
			pic.setFileUuid(uuid);
			pic.setFilePath(subDirectory);
			int i = userService.picInsert(pic);
			if (i == 0) {
				userService.picUpdate(pic);
			}
		}*/

		response.setViewName("/file/fileSample");
	}

	@RequestMapping("/file/fileView")
	public void fileView(MciRequest request, MciResponse response) {
		int id = Integer.parseInt(request.getParam("id"));
		UserPicDTO pic = userService.picInfo2(id);
		List<ContentFileDTO> fileList = contentsService.selectFile(id);
		ContentFileDTO file = fileList.get(0);
		response.setDownloadFile(new FileInfo(pic.getFilePath(), file
				.getFileUuid()));
	}

	@RequestMapping("/file/download")
	public void download(MciRequest request, MciResponse response) {
		Authentication currentUser = SecurityContextHolder.getContext()
				.getAuthentication();
		String userId = currentUser.getName().toString();
		UserPicDTO pic = userService.picInfo(userId);
		response.setDownloadFile(new FileInfo(pic.getFilePath(), pic
				.getFileUuid()));

	}
}
