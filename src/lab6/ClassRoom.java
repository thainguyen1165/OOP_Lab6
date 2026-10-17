package lab6;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class ClassRoom {
    private String roomId;
    private String name;
    private int capacity;
    private List<Device> devices = new ArrayList<>();

    public ClassRoom(String roomId, String name, int capacity) {
        if (roomId == null || roomId.isBlank() || name == null || name.isBlank() || capacity <= 0) {
            throw new IllegalArgumentException("Thong tin phong khong hop le.");
        }
        this.roomId = roomId;
        this.name = name;
        this.capacity = capacity;
    }

    public List<Device> getDevices() {
        return new ArrayList<>(devices);
    }

    public void addDevice(Device device) {
        if (device == null) {
            throw new IllegalArgumentException("Thiet bi khong duoc null.");
        }
        if (findDevice(device.getDeviceId()) != null) {
            throw new DuplicateDeviceException(device.getDeviceId());
        }
        devices.add(device);
    }

    public boolean removeDevice(String deviceId) {
        Device device = findDevice(deviceId);
        return device != null && devices.remove(device);
    }

    public Device findDevice(String deviceId) {
        for (Device device : devices) {
            if (device.getDeviceId().equals(deviceId)) {
                return device;
            }
        }
        return null;
    }

    public double calculateAnnualMaintenanceCost() {
        double total = 0;
        for (Device device : devices) {
            total += device.calculateAnnualMaintenanceCost();
        }
        return total;
    }

    public List<Device> getDevicesRequiringMaintenance() {
        List<Device> result = new ArrayList<>();
        int currentYear = Year.now().getValue();
        for (Device device : devices) {
            if (device.getStatus() == DeviceStatus.UnderMaintenance
                    || currentYear - device.getYearOfUse() > 5) {
                result.add(device);
            }
        }
        return result;
    }
}
