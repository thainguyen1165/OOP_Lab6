package lab6;

public abstract class TeachingDevice extends Device {
    private int maximumBorrowDays;
    private int maximumBorrowPeriods;
    private double dailyLateFee;

    public TeachingDevice(String id, String name, int year, double price,
            int maximumBorrowDays, int maximumBorrowPeriods, double dailyLateFee) {
        super(id, name, year, price);
        if (maximumBorrowDays <= 0 || maximumBorrowPeriods <= 0 || dailyLateFee < 0) {
            throw new IllegalArgumentException("Gioi han muon hoac phi tre khong hop le.");
        }
        this.maximumBorrowDays = maximumBorrowDays;
        this.maximumBorrowPeriods = maximumBorrowPeriods;
        this.dailyLateFee = dailyLateFee;
    }

    public int getMaximumBorrowDays() {
        return maximumBorrowDays;
    }

    public int getMaximumBorrowPeriods() {
        return maximumBorrowPeriods;
    }

    public double getDailyLateFee() {
        return dailyLateFee;
    }
}
