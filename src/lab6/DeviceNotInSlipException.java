package lab6;

public final class DeviceNotInSlipException extends BorrowingException {
    public DeviceNotInSlipException(String deviceId) {
        super("Thiet bi " + deviceId + " khong thuoc phieu muon.");
    }

}
