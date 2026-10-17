package lab6;

import java.time.LocalDate;
import java.time.Year;

public class Main {
    public static void main(String[] args) {
        int year = Year.now().getValue();
        LocalDate date = LocalDate.of(2026, 10, 16);
        Lecturer lecturer = new Lecturer("GV01", "Nguyen Van An");
        TeachingAssistant assistant = new TeachingAssistant("TG01", "Tran Thi Binh");
        ProjectorRemote projector = new ProjectorRemote("MC01", "Dieu khien may chieu", year - 2, 500000);
        AirConditionerRemote remote = new AirConditionerRemote("DH01", "Dieu khien dieu hoa", year - 6, 300000);
        WirelessMicrophone mic = new WirelessMicrophone("MI01", "Micro khong day", year - 1, 2000000);

        ClassRoom room = new ClassRoom("A101", "Phong hoc A101", 50);
        room.addDevice(projector);
        room.addDevice(remote);
        room.addDevice(mic);

        System.out.println("=== THIET BI VA BAO TRI ===");
        for (Device device : room.getDevices()) {
            System.out.println(device);
        }
        System.out.println("Tong bao tri/nam: " + room.calculateAnnualMaintenanceCost());
        System.out.println("Thiet bi can bao tri:");
        for (Device device : room.getDevicesRequiringMaintenance()) {
            System.out.println(device);
        }

        BorrowingSlip slip = new BorrowingSlip("PM01", lecturer, "A101", date);
        System.out.println("\n=== MUON VA TRA THANH CONG ===");
        try {
            slip.addDevice(projector, 1, 1, 3);
            slip.addDevice(mic, 1, 1, 3);
            System.out.println("Tra may chieu dung han, phi: "
                    + slip.returnDevice("MC01", date.plusDays(1)));
            System.out.println("Tra micro muon 2 ngay, phi: "
                    + slip.returnDevice("MI01", date.plusDays(3)));
            System.out.println(slip);
        } catch (BorrowingException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Ket thuc xu ly phieu PM01.");
        }

        System.out.println("\n=== CAC TINH HUONG NGOAI LE ===");
        BorrowingSlip errorSlip = new BorrowingSlip("PM02", lecturer, "A101", date);
        BorrowingSlip assistantSlip = new BorrowingSlip("PM03", assistant, "A102", date);

        // 1. Muon thiet bi dang bao tri.
        remote.startMaintenance();
        try {
            errorSlip.addDevice(remote, 1);
        } catch (DeviceUnavailableException e) {
            System.out.println("1. " + e.getMessage());
        }

        // 2. Tro giang muon thiet bi thu ba.
        try {
            assistantSlip.addDevice(projector, 1);
            assistantSlip.addDevice(mic, 1);
            WirelessMicrophone extra = new WirelessMicrophone("MI02", "Micro du phong", year, 1500000);
            assistantSlip.addDevice(extra, 1);
        } catch (BorrowLimitExceededException e) {
            System.out.println("2. " + e.getMessage());
        }

        // 3. Them cung micro hai lan.
        try {
            assistantSlip.addDevice(mic, 1);
        } catch (DuplicateDeviceException e) {
            System.out.println("3. " + e.getMessage());
        }

        // 4. Muon micro qua gioi han 1 ngay.
        try {
            errorSlip.addDevice(mic, 3);
        } catch (InvalidBorrowPeriodException e) {
            System.out.println("4. " + e.getMessage());
        }

        // 5. Tra thiet bi khong thuoc phieu.
        try {
            errorSlip.returnDevice("MC01", date);
        } catch (DeviceNotInSlipException e) {
            System.out.println("5. " + e.getMessage());
        }

        // 6. Tra cung thiet bi hai lan.
        try {
            slip.returnDevice("MC01", date.plusDays(2));
        } catch (DeviceAlreadyReturnedException e) {
            System.out.println("6. " + e.getMessage());
        }

        // 7. Ngay tra truoc ngay muon.
        try {
            assistantSlip.returnDevice("MI01", date.minusDays(1));
        } catch (InvalidReturnDateException e) {
            System.out.println("7. " + e.getMessage());
        }

        BorrowingSlip[] slips = { slip, errorSlip, assistantSlip };

        System.out.println("\n=== PHIEU TRA MUON HOAC DANG QUA HAN ===");
        for (BorrowingSlip s : slips) {
            if (s.hasLateDevices(date.plusDays(3))) {
                System.out.println(s);
            }
        }

        System.out.println("\n=== PHIEU CHUA TRA HET ===");
        for (BorrowingSlip s : slips) {
            if (s.hasUnreturnedDevices()) {
                System.out.println(s);
                for (BorrowItem item : s.getItems()) {
                    System.out.println("  " + item);
                }
            }
        }
    }
}
