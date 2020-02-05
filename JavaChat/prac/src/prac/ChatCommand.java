package prac;
 // 채팅 명령어
public enum ChatCommand {
	WHISPER("/w"), SEARCH("#"); // /W를 입력 시 귓속말, # 입력 시 검색
	
	private final String command;
	
	private ChatCommand(final String command) {
		this.command = command;
	}
	
	public String toString() {
		return command;
	}
}
