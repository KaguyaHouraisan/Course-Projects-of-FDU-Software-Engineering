package SQAT.exception;

public class MeetingnameHasBeenRegisteredException extends RuntimeException {
    private static final long serialVersionUID = -6074753940710869977L;

    public MeetingnameHasBeenRegisteredException(String meetingName) {
        super("MeetingName '" + meetingName + "' has been registered");
    }
}
