package src;

public abstract class Vehicle implements Maintainable {
    private final String vehicleId;
    private String brand;
    private String model;
    private int manufacturingYear;
    private double dailyRentalPrice;
    private boolean isRented;
    private boolean maintenanceRequired;

    
    public Vehicle(String vehicleId, String brand, String model, int manufacturingYear, double dailyRentalPrice) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.manufacturingYear = manufacturingYear;
        this.dailyRentalPrice = dailyRentalPrice;
        this.isRented = false;
        this.maintenanceRequired = false;
    }

 
    public abstract double calculateRentalCost(int days);
    public abstract void displayInfo();

 
    @Override
    public void performMaintenance() {
        this.maintenanceRequired = false;
        System.out.println("Maintenance successfully performed on Vehicle ID: " + vehicleId);
    }

    @Override
    public boolean needsMaintenance() {
        return this.maintenanceRequired;
    }

  
    public void flagForMaintenance() {
        this.maintenanceRequired = true;
    }

    public boolean isAvailable() {
        return !isRented && !maintenanceRequired;
    }

    public void markAsRented() {
        this.isRented = true;
    }

    public void markAsAvailable() {
        this.isRented = false;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getManufacturingYear() {
        return manufacturingYear;
    }

    public double getDailyRentalPrice() {
        return dailyRentalPrice;
    }

    public void setDailyRentalPrice(double dailyRentalPrice) {
        if (dailyRentalPrice > 0) {
            this.dailyRentalPrice = dailyRentalPrice;
        } else {
            System.out.println("Error: Daily price must be positive.");
        }
    }

    public boolean isRented() {
        return isRented;
    }
}