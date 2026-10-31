package src;

import java.util.ArrayList;

public class RentalManagement {
    private ArrayList<Vehicle> vehiclesList;
    private ArrayList<Customer> customersList;
    private ArrayList<RentalTransaction> rentalsList;
    private static int rentalIdCounter = 1000; 

    public RentalManagement() {
        this.vehiclesList = new ArrayList<>();
        this.customersList = new ArrayList<>();
        this.rentalsList = new ArrayList<>();
    }

   

    public boolean addVehicle(Vehicle vehicle) {
        if (findVehicleById(vehicle.getVehicleId()) != null) {
            System.out.println("Error: Vehicle with ID " + vehicle.getVehicleId() + " already exists!");
            return false;
        }
        vehiclesList.add(vehicle);
        System.out.println("Vehicle added successfully!");
        return true;
    }

    public Vehicle findVehicleById(String vehicleId) {
        for (Vehicle v : vehiclesList) {
            if (v.getVehicleId().equalsIgnoreCase(vehicleId)) {
                return v;
            }
        }
        return null;
    }

    public void displayAvailableVehicles() {
        System.out.println("\n====== Available Vehicles======");
        boolean found = false;
        for (Vehicle v : vehiclesList) {
            if (v.isAvailable()) {
                v.displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No vehicles are currently available for rent.");
        }
    }

    public void displayAllVehicles() {
        System.out.println("\n====== All Vehicles Fleet ======");
        if (vehiclesList.isEmpty()) {
            System.out.println("No vehicles in the system.");
            return;
        }
        for (Vehicle v : vehiclesList) {
            v.displayInfo();
        }
    }

    public void displayVehiclesUnderMaintenance() {
    boolean found = false;
    for (Vehicle vehicle : vehiclesList) {
       
        if (vehicle.needsMaintenance()) {
           
           found = true;
            System.out.println("vehicle:"+vehicle.getVehicleId()+" is under maintenance");
        }
    }
    if (!found) {
        System.out.println("No vehicles are currently flagged for maintenance.");
    }

}

    public boolean addCustomer(Customer customer) {
        if (findCustomerById(customer.getCustomerId()) != null) {
            System.out.println("Error: Customer with ID " + customer.getCustomerId() + " already exists!");
            return false;
        }
        customersList.add(customer);
        System.out.println("Customer registered successfully!");
        return true;
    }

    public Customer findCustomerById(String customerId) {
        for (Customer c : customersList) {
            if (c.getCustomerId().equalsIgnoreCase(customerId)) {
                return c;
            }
        }
        return null;
    }

    public void displayAllCustomers() {
        System.out.println("\n====== Registered Customers ======");
        if (customersList.isEmpty()) {
            System.out.println("No customers registered yet.");
            return;
        }
        for (Customer c : customersList) {
            c.displayCustomerInfo();
        }
    }

   

    public boolean processRental(String customerId, String vehicleId, int days) {
        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Error: Customer ID not found.");
            return false;
        }

        Vehicle vehicle = findVehicleById(vehicleId);
        if (vehicle == null) {
            System.out.println("Error: Vehicle ID not found.");
            return false;
        }

        
        if (!vehicle.isAvailable()) {
            if (vehicle.needsMaintenance()) {
                System.out.println("Error: Vehicle is currently out of service (Needs Maintenance).");
            } else {
                System.out.println("Error: Vehicle is already rented.");
            }
            return false;
        }

        
        double totalCost = vehicle.calculateRentalCost(days);

      
        String rentalId = "RENT-" + (++rentalIdCounter);

       
        RentalTransaction transaction = new RentalTransaction(rentalId, customer, vehicle, days, totalCost);
        rentalsList.add(transaction);
        vehicle.markAsRented();

        System.out.println("\nRental processed successfully!");
        transaction.displayTransactionDetails();
        return true;
    }

    public boolean returnVehicle(String rentalId) {
        RentalTransaction transaction = findRentalById(rentalId);
        if (transaction == null) {
            System.out.println("Error: Rental Transaction ID not found.");
            return false;
        }

        if (transaction.getStatus().equalsIgnoreCase("Completed")) {
            System.out.println("Error: This rental transaction is already completed.");
            return false;
        }

      
        transaction.completeRental();
        transaction.getVehicle().markAsAvailable();

        System.out.println("Vehicle returned successfully for Rental ID: " + rentalId);
        return true;
    }

    public RentalTransaction findRentalById(String rentalId) {
        for (RentalTransaction r : rentalsList) {
            if (r.getRentalId().equalsIgnoreCase(rentalId)) {
                return r;
            }
        }
        return null;
    }

   
public void flagVehicleForMaintenance(String vehicleId) {
    Vehicle vehicle = findVehicleById(vehicleId);

   
    if (vehicle == null) {
        System.out.println("Error: Vehicle not found.");
        return;
    }

    
    if (!vehicle.isAvailable()) {
        System.out.println("Error: Cannot flag vehicle for maintenance while it is rented!");
        return;
    }

   
    vehicle.needsMaintenance();
    System.out.println("Vehicle " + vehicleId + " flagged for maintenance successfully.");
}
   
public boolean performVehicleMaintenance(String vehicleId) {
    Vehicle vehicle = findVehicleById(vehicleId);

    if (vehicle == null) {
        System.out.println("Error: Vehicle not found.");
        return false;
    }

   
    if (!vehicle.isAvailable()) {
        System.out.println("Error: Cannot perform maintenance while the vehicle is currently rented!");
        return false;
    }

    if (!vehicle.needsMaintenance()) {
        System.out.println("Error: Vehicle does not require maintenance.");
        return false;
    }

    
    if (vehicle instanceof Maintainable) {
        Maintainable maintainableVehicle = (Maintainable) vehicle;
        maintainableVehicle.performMaintenance(); 
        System.out.println("Maintenance completed successfully for vehicle: " + vehicleId);
        return true;
    } else {
        System.out.println("Error: Vehicle does not implement Maintainable interface.");
        return false;
    }
}
   

    public double calculateTotalRevenue() {
        double totalRevenue = 0.0;
        for (RentalTransaction r : rentalsList) {
            if (r.getStatus().equalsIgnoreCase("Completed")) {
                totalRevenue += r.getTotalCost();
            }
        }
        return totalRevenue;
    }

    public void displayCustomerRentalHistory(String customerId) {
        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Error: Customer ID not found.");
            return;
        }

        System.out.println("\n====== Rental History for Customer: " + customer.getName() + " ======");
        boolean found = false;
        for (RentalTransaction r : rentalsList) {
            if (r.getCustomer().getCustomerId().equalsIgnoreCase(customerId)) {
                r.displayTransactionDetails();
                found = true;
            }
            System.out.println("==================================================");
        }
        if (!found) {
            System.out.println("No rental history found for this customer.");
        }
    }

    public void displayAllTransactions() {
        System.out.println("\n====== All Rental Transactions ======");
        if (rentalsList.isEmpty()) {
            System.out.println("No transactions recorded yet.");
            return;
        }
        for (RentalTransaction r : rentalsList) {
            r.displayTransactionDetails();
        }
        System.out.println("==================================================");
    }
}