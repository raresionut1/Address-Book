package agenda;

public class ContactDuplicatException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ContactDuplicatException() {
		super("Contactul exista deja in agenda!");
	}
}
