import java.util.ArrayList;
import java.util.List;

class Automobile {
    private String make;
    private String model;
    private String color;
    private int year;
    private int mileage;

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
    }

    public static String addVehicle(String make, String model, String color, int year, int mileage) {
        inventory.add(new Automobile(make, model, color, year, mileage));
        return "Vehicle added successfully.";
    }

    public static String removeVehicle(String make, String model, String color, int year, int mileage) {
        return inventory.removeIf(car ->
                car.getMake().equals(make)
                && car.getModel().equals(model)
                && car.getColor().equals(color)
                && car.getYear() == year
                && car.getMileage() == mileage
        ) ? "Vehicle removed successfully." : "No matching vehicle found to remove.";
    }

    public static List<String> listVehicle() {
        if (inventory.isEmpty()) {
            return List.of("Inventory is empty.");
        }
        return inventory.stream()
                .map(Automobile::getInfo)
                .toList();
    }
}
