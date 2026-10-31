package src;

import java.util.Scanner;

public class ConsoleUI {
    private RentalManagement system;
    private Scanner scanner;

    public ConsoleUI(RentalManagement system) {
        this.system = system;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            displayMenu();
            int choice = readIntInput("Select an option (0-14): ");
            running = processMenuOption(choice);
        }
        
        scanner.close();
    }

    private void displayMenu() {
        System.out.println("\n==================================================");
        System.out.println("Welcome to the vehicle rental system! Please select an option from the menu below:");
        System.out.println("==================================================");
        System.out.println("1. Add New Vehicle");
        System.out.println("2. Display All Vehicles");
        System.out.println("3. Search for Vehicle");
        System.out.println("4. Display Available Vehicles");
        System.out.println("5. Register New Customer");
        System.out.println("6. Display All Customers");
        System.out.println("7. Rent a Vehicle");
        System.out.println("8. Return a Vehicle");
        System.out.println("9. Display All Rentals");
        System.out.println("10. Display Customer Rental History");
        System.out.println("11. Display Total Revenue");
        System.out.println("12. Flag Vehicle for Maintenance");
        System.out.println("13. Display Vehicles Requiring Maintenance");
        System.out.println("14. Perform Vehicle Maintenance");
        System.out.println("0. Exit System");
        System.out.println("==================================================");
    }

    private boolean processMenuOption(int choice) {
        switch (choice) {
            case 1:
                handleAddVehicle();
                break;
            case 2:
                system.displayAllVehicles();
                break;
            case 3:
                handleSearchVehicle();
                break;
            case 4:
                system.displayAvailableVehicles();
                break;
            case 5:
                handleRegisterCustomer();
                break;
            case 6:
                system.displayAllCustomers();
                break;
            case 7:
                handleProcessRental();
                break;
            case 8:
                handleReturnVehicle();
                break;
            case 9:
                system.displayAllTransactions();
                break;
            case 10:
                handleDisplayCustomerHistory();
                break;
            case 11:
                System.out.println("\nTotal Completed Rental Revenue: $" + system.calculateTotalRevenue());
                break;
            case 12:
                handleFlagMaintenance();
                break;
            case 13:
                system.displayVehiclesUnderMaintenance();
                break;
            case 14:
                handlePerformMaintenance();
                break;
            case 0:
                System.out.println("Exiting System. Goodbye!");
                return false;
            default:
                System.out.println("Invalid choice! Please select an option between 0 and 14.");
        }
        return true;
    }

    private void handleAddVehicle() {
        int type = readIntInput("Select Vehicle Type (1: Car, 2: Motorcycle, 3: Truck): ");

        System.out.print("Enter Vehicle ID: ");
        String vId = scanner.nextLine().trim();
        System.out.print("Enter Brand: ");
        String brand = scanner.nextLine().trim();
        System.out.print("Enter Model: ");
        String model = scanner.nextLine().trim();
        
        int year = readIntInput("Enter Manufacturing Year: ");
        double price = readDoubleInput("Enter Daily Rental Price: ");

        if (type == 1) {
            int seats = readIntInput("Enter Seats Count: ");
            System.out.print("Enter Transmission Type (Automatic/Manual): ");
            String trans = scanner.nextLine().trim();
            boolean ins = readBooleanInput("Include Insurance? (true/false): ");

            system.addVehicle(new Car(vId, brand, model, year, price, seats, trans, ins));
        } else if (type == 2) {
            int cc = readIntInput("Enter Engine Capacity (CC): ");
            boolean helmet = readBooleanInput("Include Helmet Option? (true/false): ");

            system.addVehicle(new Motorcycle(vId, brand, model, year, price, cc, helmet));
        } else if (type == 3) {
            double load = readDoubleInput("Enter Max Load Capacity (Tons): ");

            system.addVehicle(new Truck(vId, brand, model, year, price, load));
        } else {
            System.out.println("Invalid vehicle type selection!");
        }
    }

    private void handleSearchVehicle() {
        System.out.print("Enter Vehicle ID to search: ");
        String vId = scanner.nextLine().trim();
        Vehicle vehicle = system.findVehicleById(vId);
        
        if (vehicle != null) {
            System.out.println("\nVehicle Found:");
            vehicle.displayInfo();
        } else {
            System.out.println("Error: Vehicle with ID '" + vId + "' not found.");
        }
    }

    private void handleRegisterCustomer() {
        System.out.print("Enter Customer ID: ");
        String cId = scanner.nextLine().trim();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine().trim();
        System.out.print("Enter Email Address: "); 
        String email = scanner.nextLine().trim();
        System.out.print("Enter Driver License Number: ");
        String license = scanner.nextLine().trim();

        
        system.addCustomer(new Customer(cId, name, phone, email, license));
    }

    private void handleProcessRental() {
        System.out.print("Enter Customer ID: ");
        String rentCId = scanner.nextLine().trim();
        System.out.print("Enter Vehicle ID: ");
        String rentVId = scanner.nextLine().trim();
        
        int days = 0;
        while (true) {
            days = readIntInput("Enter Rental Duration (Days): ");
            if (days > 0) {
                break; 
            }
            System.out.println("Error: Rental duration must be positive!");
        }

        system.processRental(rentCId, rentVId, days);
    }

    private void handleReturnVehicle() {
        System.out.print("Enter Rental Transaction ID: ");
        String rId = scanner.nextLine().trim();
        system.returnVehicle(rId);
    }

   private void handlePerformMaintenance() {
    System.out.print("Enter Vehicle ID to Perform Maintenance: ");
    String mainVId = scanner.nextLine().trim();
    
    system.performVehicleMaintenance(mainVId);
}

    private void handleFlagMaintenance() {
        System.out.print("Enter Vehicle ID to Flag for Maintenance: ");
    
        String flagVId = scanner.nextLine().trim();
        system.flagVehicleForMaintenance(flagVId);
    }

    private void handleDisplayCustomerHistory() {
        System.out.print("Enter Customer ID for History: ");
        String historyCId = scanner.nextLine().trim();
        system.displayCustomerRentalHistory(historyCId);
    }

    private int readIntInput(String prompt) {
        while (true) {
            if (!prompt.isEmpty()) {
                System.out.print(prompt);
            }
            try {
                String input = scanner.nextLine().trim();
                if (!input.isEmpty()) {
                    return Integer.parseInt(input);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
            }
        }
    }

    private double readDoubleInput(String prompt) {
        while (true) {
            if (!prompt.isEmpty()) {
                System.out.print(prompt);
            }
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid decimal number.");
            }
        }
    }

    private boolean readBooleanInput(String prompt) {
        while (true) {
            if (!prompt.isEmpty()) {
                System.out.print(prompt);
            }
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("true") || input.equals("yes") || input.equals("y") || input.equals("1")) {
                return true;
            } else if (input.equals("false") || input.equals("no") || input.equals("n") || input.equals("0")) {
                return false;
            }
            System.out.println("Invalid input! Please enter 'true' or 'false'.");
        }
    }
}