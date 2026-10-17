package lab6;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BorrowingSlip {
    private final String slipId;
    private IBorrower borrower;
    private String teachingRoom;
    private LocalDate borrowedDate;
    private List<BorrowItem> items = new ArrayList<>();

    public BorrowingSlip(String slipId, IBorrower borrower, String teachingRoom, LocalDate borrowedDate) {
        if (slipId == null || slipId.isBlank() || borrower == null
                || teachingRoom == null || teachingRoom.isBlank() || borrowedDate == null) {
            throw new IllegalArgumentException("Thong tin phieu muon khong hop le.");
        }
        this.slipId = slipId;
        this.borrower = borrower;
        this.teachingRoom = teachingRoom;
        this.borrowedDate = borrowedDate;
    }

    public List<BorrowItem> getItems() {
        return new ArrayList<>(items);
    }

    public void addDevice(TeachingDevice device, int borrowDays) {
        addDevice(device, borrowDays, 1, 1);
    }

    public void addDevice(TeachingDevice device, int borrowDays, int fromPeriod, int toPeriod) {
        if (device == null) {
            throw new IllegalArgumentException("Thiet bi khong duoc null.");
        }
        if (borrowDays <= 0 || borrowDays > device.getMaximumBorrowDays()) {
            throw new InvalidBorrowPeriodException(device.getDeviceId(),
                    borrowDays, device.getMaximumBorrowDays());
        }
        for (BorrowItem item : items) {
            if (item.getDevice().getDeviceId().equals(device.getDeviceId())) {
                throw new DuplicateDeviceException(device.getDeviceId());
            }
        }
        if (items.size() >= borrower.getMaximumDevices()) {
            throw new BorrowLimitExceededException(borrower.getBorrowerId(), borrower.getMaximumDevices());
        }
        if (device.getStatus() != DeviceStatus.Available) {
            throw new DeviceUnavailableException(device.getDeviceId(), device.getStatus());
        }

        // Kiem tra xong moi thay doi trang thai va them vao phieu.
        BorrowItem item = new BorrowItem(device, borrowedDate,
                borrowedDate.plusDays(borrowDays), fromPeriod, toPeriod);
        device.markAsBorrowed();
        items.add(item);
    }

    public double returnDevice(String deviceId, LocalDate returnedDate) {
        if (deviceId == null || deviceId.isBlank()) {
            throw new IllegalArgumentException("Ma thiet bi khong duoc rong.");
        }
        for (BorrowItem item : items) {
            if (item.getDevice().getDeviceId().equals(deviceId)) {
                item.returnDevice(returnedDate);
                return item.calculateLateFee();
            }
        }
        throw new DeviceNotInSlipException(deviceId);
    }

    public double calculateTotalLateFee() {
        double total = 0;
        for (BorrowItem item : items) {
            total += item.calculateLateFee();
        }
        return total;
    }

    public boolean hasUnreturnedDevices() {
        for (BorrowItem item : items) {
            if (!item.isReturned()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasLateDevices(LocalDate today) {
        for (BorrowItem item : items) {
            LocalDate date = item.isReturned() ? item.getReturnedDate() : today;
            if (date.isAfter(item.getDueDate())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return slipId + " - " + borrower.getFullName() + " - Phong: " + teachingRoom
                + " - Tong phi tre: " + calculateTotalLateFee();
    }
}
