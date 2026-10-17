package lab6;

public final class DeviceAlreadyReturnedException extends BorrowingException {
    public DeviceAlreadyReturnedException(String deviceId) {
        super("Thiet bi " + deviceId + " da duoc tra truoc do.");
    }

}
