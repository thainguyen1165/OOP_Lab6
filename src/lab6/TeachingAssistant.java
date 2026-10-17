package lab6;

public class TeachingAssistant implements IBorrower {
    private String borrowerId;
    private String fullName;

    public TeachingAssistant(String borrowerId, String fullName) {
        if (borrowerId == null || borrowerId.isBlank() || fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Thong tin tro giang khong hop le.");
        }
        this.borrowerId = borrowerId;
        this.fullName = fullName;
    }

    @Override
    public String getBorrowerId() {
        return borrowerId;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public int getMaximumDevices() {
        return 2;
    }
}
