package jcf.edu.file.controller;

import jcf.sua.mvc.MciRequest;
import jcf.sua.mvc.MciResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FileController {

	@RequestMapping(value="/download/file/**/*", method= RequestMethod.POST)
	public void downloadUserPic(MciRequest mciRequest, MciResponse mciResponse){
		
	}
}
