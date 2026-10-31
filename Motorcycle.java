package src;

public class Motorcycle extends Vehicle {
    private int engineCapacityCC;
    private boolean hasHelmetOption;
    private static final double HELMET_FEE_PER_DAY = 5.0; 

   
    public Motorcycle(String vehicleId, String brand, String model, int manufacturingYear, 
                      double dailyRentalPrice, int engineCapacityCC, boolean hasHelmetOption) {
        super(vehicleId, brand, model, manufacturingYear, dailyRentalPrice);
        this.engineCapacityCC = engineCapacityCC;
        this.hasHelmetOption = hasHelmetOption;
    }

    
    public Motorcycle(String vehicleId, String brand, String model, int manufacturingYear, 
                      double dailyRentalPrice, int engineCapacityCC) {
        this(vehicleId, brand, model, manufacturingYear, dailyRentalPrice, engineCapacityCC, false);
    }

    @Override
    public double calculateRentalCost(int days) {
        double totalCost = getDailyRentalPrice() * days;
        if (hasHelmetOption) {
            totalCost += (HELMET_FEE_PER_DAY * days);
        }
        return totalCost;
    }

    @Override
    public void displayInfo() {
        System.err.println("==================================================");
        System.out.println("[Motorcycle] ID: " + getVehicleId() 
            + " | Brand: " + getBrand() 
            + " | Model: " + getModel() 
            + " | Year: " + getManufacturingYear() 
            + " | Price/Day: $" + getDailyRentalPrice() 
            + " | Engine: " + engineCapacityCC + " CC" 
            + " | Helmet Included: " + (hasHelmetOption ? "Yes" : "No") 
            + " | Status: " + (isAvailable() ? "Available" : (isRented() ? "Rented" : "Needs Maintenance")));
        System.err.println("==================================================");
    }

   
    public int getEngineCapacityCC() { return engineCapacityCC; }
    public boolean hasHelmetOption() { return hasHelmetOption; }
    public void setHasHelmetOption(boolean hasHelmetOption) { this.hasHelmetOption = hasHelmetOption; }
}