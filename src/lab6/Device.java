package lab6;

import java.time.Year;

public abstract class Device {
    private final String deviceId;
    private String name;
    private int yearOfUse;
    private double purchasePrice;
    private DeviceStatus status;

    public Device(String deviceId, String name, int yearOfUse, double purchasePrice) {
        // Cac rang buoc
        if (deviceId == null || deviceId.isBlank()) {
            throw new IllegalArgumentException("Ma thiet bi khong duoc rong.");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Ten thiet bi khong duoc rong.");
        }
        if (purchasePrice <= 0 || !Double.isFinite(purchasePrice)) {
            throw new IllegalArgumentException("Gia mua phai lon hon 0.");
        }
        if (yearOfUse <= 0 || yearOfUse > Year.now().getValue()) {
            throw new IllegalArgumentException("Nam su dung khong hop le.");
        }
        this.deviceId = deviceId;
        this.name = name;
        this.yearOfUse = yearOfUse;
        this.purchasePrice = purchasePrice;
        this.status = DeviceStatus.Available;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public int getYearOfUse() {
        return yearOfUse;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public DeviceStatus getStatus() {
        return status;
    }

    public void startMaintenance() {
        if (status != DeviceStatus.Available) {
            throw new DeviceUnavailableException(deviceId, status);
        }
        status = DeviceStatus.UnderMaintenance;
    }

    void markAsBorrowed() {
        if (status != DeviceStatus.Available) {
            throw new DeviceUnavailableException(deviceId, status);
        }
        status = DeviceStatus.Borrowed;
    }

    void markAsReturned() {
        if (status != DeviceStatus.Borrowed) {
            throw new DeviceUnavailableException(deviceId, status);
        }
        status = DeviceStatus.Available;
    }

    public abstract double calculateAnnualMaintenanceCost();

    public String toString() {
        return deviceId + " - " + name + " - Nam: " + yearOfUse
                + " - Gia: " + purchasePrice + " - " + status;
    }
}
