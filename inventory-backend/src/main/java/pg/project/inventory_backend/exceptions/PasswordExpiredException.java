package pg.project.inventory_backend.exceptions;

public class PasswordExpiredException extends RuntimeException {
    public PasswordExpiredException(String message) {
        super(message);
    }
}