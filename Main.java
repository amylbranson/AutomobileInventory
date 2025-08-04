import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

class Automobile {
    private String make;
    private String model;
    private String color;
    private int year;
    private int mileage;

    public Automobile() {
        this.make = "Unknown";
        this.model = "Unknown";
        this.color = "Unknown";
        this.year = 0;
        this.mileage = 0;
    }

    public Automobile(String make, String model, String color, int year, int mileage) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.year = year;
        this.mileage = mileage;
    }

    public String getMake() { return make; }
    public String getModel() { return model; }
    public String getColor() { return color; }
    public int getYear() { return year; }
    public int getMileage() { return mileage; }

    public String getInfo() {
        return String.format("%d %s %s, Mileage: %d", year, make, model, mileage);
    }
}

public class Main {
    private static final List<Automobile> inventory = new ArrayList<>();

    public static void main(String[] args) {
        listVehicle().forEach(System.out::println);

        System.out.println("\nRemoving vehicle...");
        System.out.println(removeVehicle("Toyota", "Camry", "Blue", 2020, 50000));

        System.out.println("\nAdding new vehicle...");
        System.out.println(addVehicle("Honda", "Civic", "Red", 2022, 10000));

        System.out.println("\nNew Vehicle Info:");
        listVehicle().forEach(System.out::println);

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nDo you want to print the vehicle information to a file? (Y or N): ");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("Y")) {
            try {
                FileWriter writer = new FileWriter("Autos.txt");
                for (String vehicleInfo : listVehicle()) {
                    writer.write(vehicleInfo + "\n");
                }
                writer.close();
                System.out.println("Vehicle information written to file successfully.");
            } catch (IOException e) {
                System.out.println("An error occurred while writing to file: " + e.getMessage());
            }
        } else {
            System.out.println("File will not be printed.");
        }
    }

    public static String addVehicle(String make, String model, String color, int year, int mileage) {
        inventory.add(new Automobile(make, model, color, year, mileage));
        return "Vehicle added successfully.";
    }

    public static String removeVehicle(String make, String model, String color, int year, int mileage) {
        try {
            boolean removed = inventory.removeIf(car ->
                car.getMake().equals(make)
                && car.getModel().equals(model)
                && car.getColor().equals(color)
                && car.getYear() == year
                && car.getMileage() == mileage
            );
            return removed ? "Vehicle removed successfully." : "No matching vehicle found to remove.";
        } catch (Exception e) {
            return "Failed to remove vehicle: " + e.getMessage();
        }
    }

    public static List<String> listVehicle() {
        if (inventory.isEmpty()) {
            return List.of("Inventory is empty.");
        }
        return inventory.stream()
                .map(Automobile::getInfo)
                .toList();
    }

    public static String updateVehicle(String oldMake, String oldModel, String oldColor, int oldYear, int oldMileage,
                                       String newMake, String newModel, String newColor, int newYear, int newMileage) {
        try {
            for (Automobile car : inventory) {
                if (car.getMake().equals(oldMake)
                    && car.getModel().equals(oldModel)
                    && car.getColor().equals(oldColor)
                    && car.getYear() == oldYear
                    && car.getMileage() == oldMileage) {

                    inventory.remove(car);
                    inventory.add(new Automobile(newMake, newModel, newColor, newYear, newMileage));
                    return "Vehicle updated successfully.";
                }
            }
            return "No matching vehicle found to update.";
        } catch (Exception e) {
            return "Failed to update vehicle: " + e.getMessage();
        }
    }
}
