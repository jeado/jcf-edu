package jcf.edu.file.model;

public class ContentFileDTO {
	private int contentsId;
	private String fileUuid;

	public int getContentsId() {
		return contentsId;
	}

	public void setContentsId(int contentsId) {
		this.contentsId = contentsId;
	}

	public String getFileUuid() {
		return fileUuid;
	}

	public void setFileUuid(String fileUuid) {
		this.fileUuid = fileUuid;
	}

	@Override
	public String toString() {
		return "ContentFileDTO [contentsId=" + contentsId + ", fileUuid="
				+ fileUuid + "]";
	}

}
