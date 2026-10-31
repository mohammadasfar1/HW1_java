package src;


public class Main {
    public static void main(String[] args) {
        RentalManagement rentalManagement = new RentalManagement();
      //some data for some test
rentalManagement.addVehicle(new Car("C101", "Toyota", "Camry", 2022, 60.0, 5, "Automatic", true));
        rentalManagement.addVehicle(new Motorcycle("M101", "Yamaha", "R6", 2021, 40.0, 600, true));
        rentalManagement.addVehicle(new Truck("T101", "Volvo", "FH16", 2020, 150.0, 12.5));
        rentalManagement.addCustomer(new Customer("CUST1", "Ahmed Ali", "0599123456", "DL-998877"));

rentalManagement.flagVehicleForMaintenance("T101");


Maintainable maintainableVehicle = (Maintainable) rentalManagement.findVehicleById("T101");

if (maintainableVehicle != null && maintainableVehicle.needsMaintenance()) {
    System.out.println("--- Testing Maintenance via Maintainable Reference ---");
    maintainableVehicle.performMaintenance(); 
}


        ConsoleUI consoleUI = new ConsoleUI(rentalManagement);
        consoleUI.start();
    }
}