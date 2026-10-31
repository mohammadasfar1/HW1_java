package src;

public class Truck extends Vehicle {
    private double maxLoadCapacityTon;
    private static final double FIXED_SERVICE_FEE = 50.0; 

    public Truck(String vehicleId, String brand, String model, int manufacturingYear, 
                 double dailyRentalPrice, double maxLoadCapacityTon) {
        super(vehicleId, brand, model, manufacturingYear, dailyRentalPrice);
        this.maxLoadCapacityTon = maxLoadCapacityTon;
    }

    @Override
    public double calculateRentalCost(int days) {
       
        return (getDailyRentalPrice() * days) + FIXED_SERVICE_FEE;
    }

    @Override
    public void displayInfo() {
        System.out.println("==================================================");
        System.out.println("[Truck] ID: " + getVehicleId() 
            + " | Brand: " + getBrand() 
            + " | Model: " + getModel() 
            + " | Year: " + getManufacturingYear() 
            + " | Price/Day: $" + getDailyRentalPrice() 
            + " | Max Load: " + maxLoadCapacityTon + " Tons" 
            + " | Service Fee: $" + FIXED_SERVICE_FEE 
            + " | Status: " + (isAvailable() ? "Available" : (isRented() ? "Rented" : "Needs Maintenance")));
        System.out.println("==================================================");
    }

   
    public double getMaxLoadCapacityTon() { return maxLoadCapacityTon; }
    public void setMaxLoadCapacityTon(double maxLoadCapacityTon) { this.maxLoadCapacityTon = maxLoadCapacityTon; }
}
