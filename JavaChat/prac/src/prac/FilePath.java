package prac;

public enum FilePath {

	PROFILEPATH("Image/profile/"),
	EMOTICONPATH("Image/emoticon/"),
	DOWNLOADFILEPATH("Image/download/");
	
	private final String path;
	
	private FilePath(final String path) {
		this.path = path;
	}
	
	public String toString() {
		return path;
	}
}
