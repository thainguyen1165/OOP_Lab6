package lab6;

//Dieu khien may chieu
public class ProjectorRemote extends TeachingDevice {
    public ProjectorRemote(String id, String name, int year, double price) {
        super(id, name, year, price, 2, 5, 15000);
    }

    public double calculateAnnualMaintenanceCost() {
        return getPurchasePrice() * 0.06;
    }
}
