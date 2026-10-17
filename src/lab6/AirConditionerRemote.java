package lab6;

//Dieu khien dieu hoa
public class AirConditionerRemote extends TeachingDevice {
    public AirConditionerRemote(String id, String name, int year, double price) {
        super(id, name, year, price, 2, 5, 10000);
    }

    public double calculateAnnualMaintenanceCost() {
        return getPurchasePrice() * 0.1;
    }
}
