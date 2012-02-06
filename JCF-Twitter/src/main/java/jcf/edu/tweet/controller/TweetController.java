package jcf.edu.tweet.controller;

import java.io.IOException;
import java.util.List;

import jcf.edu.file.model.ContentFileDTO;
import jcf.edu.tweet.model.TweetDTO;
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

@Controller
public class TweetController {

	@Autowired
	private TwitService contentsService;

	@Autowired
	private UserService userService;

	@RequestMapping("/twitters/list")
	public void listTwitter(MciRequest request, MciResponse response) {

		Authentication currentUser = SecurityContextHolder.getContext()
				.getAuthentication();

		String currentId = currentUser.getName().toString();
		List<TweetDTO> content = contentsService.selectMyContents(currentId);

		response.setList("tweetList", content);
		response.setViewName("twitter");

		String tweets = request.getParam("tweets");
		if (tweets == null)
			response.set("nullTweetsCheck", 1);
		else if (tweets.trim().isEmpty() == false)
			response.set("nullTweetsCheck", 1);
		else
			response.set("nullTweetsCheck", 0);

		List<ContentFileDTO> contentFile = contentsService.selectFile2();
		response.setList("fileList", contentFile);
	}

	@SuppressWarnings("deprecation")
	@RequestMapping("/twitters/insert")
	public void insertTwitter(MciRequest request, MciResponse response)
			throws IOException {
		Authentication currentUser = SecurityContextHolder.getContext()
				.getAuthentication();
		String userId = currentUser.getName().toString();

		String register = request.getParam("register");
		String tweets = request.getParam("tweets");
		TweetDTO content = new TweetDTO();
		content.setRegister(register);
		content.setTweets(tweets);

		List<FileInfo> list = request.getAttachments();

		FileInfo fileInfo = list.get(0);
		String uuid = fileInfo.getCallName();

		if (uuid.isEmpty() == false) {
			UserPicDTO pic = new UserPicDTO();
			pic.setUserId(userId);
			pic.setFileName(fileInfo.getName());
			pic.setFileUuid(uuid);
			pic.setFilePath("tweet/userPic");
			userService.picInsert(pic);
		}

		contentsService.insertContent(content, uuid, fileInfo.getName());

		response.set("tweets", tweets);
		response.setViewName("redirect:list.action");
	}

	@RequestMapping("/twitters/delete")
	public void deleteTwitter(MciRequest request, MciResponse response) {
		int id = Integer.parseInt(request.getParam("id"));
		contentsService.deleteContent(id);
		response.setViewName("redirect:list.action");
	}

}
