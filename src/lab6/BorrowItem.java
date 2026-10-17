package lab6;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BorrowItem {
    private TeachingDevice device;
    private LocalDate borrowedDate;
    private LocalDate dueDate;
    private int fromPeriod;
    private int toPeriod;
    private LocalDate returnedDate;

    // Phieu muon tao va quan ly cac BorrowItem.
    BorrowItem(TeachingDevice device, LocalDate borrowedDate, LocalDate dueDate,
            int fromPeriod, int toPeriod) {
        if (device == null || borrowedDate == null || dueDate == null) {
            throw new IllegalArgumentException("Thiet bi va ngay khong duoc null.");
        }
        if (dueDate.isBefore(borrowedDate)) {
            throw new InvalidBorrowPeriodException(device.getDeviceId(), 0,
                    device.getMaximumBorrowDays(), "Han tra khong duoc truoc ngay muon.");
        }
        if (fromPeriod <= 0 || toPeriod < fromPeriod
                || (long) toPeriod - fromPeriod + 1 > device.getMaximumBorrowPeriods()) {
            throw new InvalidBorrowPeriodException(device.getDeviceId(), 0,
                    device.getMaximumBorrowDays(), "Khoang tiet muon khong hop le.");
        }
        this.device = device;
        this.borrowedDate = borrowedDate;
        this.dueDate = dueDate;
        this.fromPeriod = fromPeriod;
        this.toPeriod = toPeriod;
    }

    public TeachingDevice getDevice() {
        return device;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnedDate() {
        return returnedDate;
    }

    public boolean isReturned() {
        return returnedDate != null;
    }

    public void returnDevice(LocalDate date) {
        if (isReturned()) {
            throw new DeviceAlreadyReturnedException(device.getDeviceId());
        }
        if (date == null || date.isBefore(borrowedDate)) {
            throw new InvalidReturnDateException("Ngay tra khong duoc truoc ngay muon.");
        }
        device.markAsReturned();
        returnedDate = date;
    }

    public double calculateLateFee() {
        if (!isReturned()) {
            return 0;
        }
        long lateDays = Math.max(0, ChronoUnit.DAYS.between(dueDate, returnedDate));
        return lateDays * device.getDailyLateFee();
    }

    public String toString() {
        return device.getDeviceId() + " - Han tra: " + dueDate
                + " - Tiet: " + fromPeriod + "-" + toPeriod
                + " - Ngay tra: " + (isReturned() ? returnedDate : "Chua tra");
    }
}
