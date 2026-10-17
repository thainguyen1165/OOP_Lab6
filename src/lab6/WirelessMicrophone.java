package lab6;

public class WirelessMicrophone extends TeachingDevice {
    public WirelessMicrophone(String id, String name, int year, double price) {
        super(id, name, year, price, 1, 3, 20000);
    }

    public double calculateAnnualMaintenanceCost() {
        return getPurchasePrice() * 0.10;
    }
}
