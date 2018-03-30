package pl.sternik.dp.exceptions;

public class NoSuchBookException extends Exception {
    private static final long serialVersionUID = -8555511053844242536L;

    public NoSuchBookException(String string) {
		super(string);
	}

	public NoSuchBookException() {
	}


}
