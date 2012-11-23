package jcf.edu.user.model;

public class UserPicDTO {

	private String fileUuid;
	private String fileName;
	private String filePath;
	private String userId;

	public String getFileUuid() {
		return fileUuid;
	}

	public void setFileUuid(String fileUuid) {
		this.fileUuid = fileUuid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UserPicDTO [fileUuid=" + fileUuid + ", fileName=" + fileName
				+ ", filePath=" + filePath + ", userId=" + userId + "]";
	}

}
