package src;

public class RentalTransaction {
    private final String rentalId; 
    private final Customer customer; 
    private final Vehicle vehicle;   
    private int rentalDays;
    private double totalCost;
    private String status; 

    
    public RentalTransaction(String rentalId, Customer customer, Vehicle vehicle, int rentalDays, double totalCost) {
        this.rentalId = rentalId;
        this.customer = customer;
        this.vehicle = vehicle;
        this.rentalDays = rentalDays;
        this.totalCost = totalCost;
        this.status = "Active"; 
    }

    
    public void completeRental() {
        this.status = "Completed";
    }

    
    public void displayTransactionDetails() {
        System.out.println("==================================================");
        System.out.println("Rental ID    : " + rentalId);
        System.out.println("Status       : " + status);
        System.out.println("Customer     : " + customer.getName() + " (ID: " + customer.getCustomerId() + ")");
        System.out.println("Vehicle      : " + vehicle.getBrand() + " " + vehicle.getModel() + " (ID: " + vehicle.getVehicleId() + ")");
        System.out.println("Duration     : " + rentalDays + " Days");
        System.out.println("Total Cost   : $" + totalCost);
        System.out.println("==================================================");
    }

    
    public String getRentalId() {
        return rentalId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public String getStatus() {
        return status;
    }
}