package system.file;


import javax.servlet.http.HttpServletRequest;

import jcf.upload.FileInfo;
import jcf.upload.MultiPartInfo;
import jcf.upload.handler.UploadEventHandler;
import jcf.upload.persistence.PersistenceManager;

import org.springframework.util.StringUtils;

public class UserPicUploadEventHandler implements UploadEventHandler {

	private static final String replaceRegExpr = "[/_a-zA-Z0-9-]*/(up|down)load/(file|db)/";

	public long getMaxUploadSize() {
		return 10000000;
	}

	public String getFolder(HttpServletRequest request) {
		String subDirectory = "";
		if (!StringUtils.hasText(subDirectory)) {
			String requestURI = request.getRequestURI();
			subDirectory = requestURI.replaceFirst(replaceRegExpr, "");
		}
		return subDirectory;
	}

	public void postprocess(String folder, MultiPartInfo info,
			PersistenceManager persistenceManager) {

	}

	public String createFileNameIfAccepted(String folder, FileInfo fileInfo) {
		String name = fileInfo.getName();
		String filename = StringUtils.getFilename(name.replace("\\", "/"));
		return filename;
	}

	public void prepareStorage(PersistenceManager persistenceManager,
			String folder) {
	}

}
