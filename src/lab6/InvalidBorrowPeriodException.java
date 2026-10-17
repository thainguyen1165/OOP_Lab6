package lab6;

public final class InvalidBorrowPeriodException extends BorrowingException {
    private final String deviceId;
    private final long requestedDays;
    private final int maximumDays;

    public InvalidBorrowPeriodException(String deviceId, long requestedDays, int maximumDays) {
        this(deviceId, requestedDays, maximumDays, "So ngay muon phai tu 1 den " + maximumDays + ".");
    }

    public InvalidBorrowPeriodException(String deviceId, long requestedDays, int maximumDays, String detail) {
        super("Thiet bi " + deviceId + ": " + detail + " Yeu cau: " + requestedDays + " ngay.");
        this.deviceId = deviceId;
        this.requestedDays = requestedDays;
        this.maximumDays = maximumDays;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public long getRequestedDays() {
        return requestedDays;
    }

    public int getMaximumDays() {
        return maximumDays;
    }
}
