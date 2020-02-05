package prac;
// 중복 시
public class DuplicateUsernameException extends Exception {

	private static final long serialVersionUID = 1L;

	public DuplicateUsernameException(String message) {
		super(message);
	}
}
