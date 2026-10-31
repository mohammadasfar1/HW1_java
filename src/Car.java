package src;

public class Car extends Vehicle {
    private int seatsCount;
    private String transmissionType; 
    private boolean hasInsurance;
    private static final double INSURANCE_FEE_PER_DAY = 15.0; 

    
    public Car(String vehicleId, String brand, String model, int manufacturingYear, 
               double dailyRentalPrice, int seatsCount, String transmissionType, boolean hasInsurance) {
        super(vehicleId, brand, model, manufacturingYear, dailyRentalPrice);
        this.seatsCount = seatsCount;
        this.transmissionType = transmissionType;
        this.hasInsurance = hasInsurance;
    }

    
    public Car(String vehicleId, String brand, String model, int manufacturingYear, 
               double dailyRentalPrice, int seatsCount, String transmissionType) {
        this(vehicleId, brand, model, manufacturingYear, dailyRentalPrice, seatsCount, transmissionType, false);
    }

    @Override
    public double calculateRentalCost(int days) {
        double totalCost = getDailyRentalPrice() * days;
        if (hasInsurance) {
            totalCost += (INSURANCE_FEE_PER_DAY * days);
        }
        return totalCost;
    }

    @Override
    public void displayInfo() {
        System.out.println("==================================================");
        System.out.println("[Car] ID: " + getVehicleId() 
            + " | Brand: " + getBrand() 
            + " | Model: " + getModel() 
            + " | Year: " + getManufacturingYear() 
            + " | Price/Day: $" + getDailyRentalPrice() 
            + " | Seats: " + seatsCount 
            + " | Transmission: " + transmissionType 
            + " | Insurance Included: " + (hasInsurance ? "Yes" : "No") 
            + " | Status: " + (isAvailable() ? "Available" : (isRented() ? "Rented" : "Needs Maintenance")));
        System.out.println("==================================================");
    }

 
    public int getSeatsCount() { return seatsCount; }
    public String getTransmissionType() { return transmissionType; }
    public boolean hasInsurance() { return hasInsurance; }
    public void setHasInsurance(boolean hasInsurance) { this.hasInsurance = hasInsurance; }
}