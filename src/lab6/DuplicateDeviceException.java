package lab6;

public final class DuplicateDeviceException extends BorrowingException {
    public DuplicateDeviceException(String deviceId) {
        super("Thiet bi " + deviceId + " da ton tai trong danh sach.");
    }

}
