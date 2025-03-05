import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class DigitalCarbonFootprintTracker {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Wlcome to the Digital Carbon Footprint Tracker!");
		
		System.out.print("Enter miles driven today: ");
		double milesDriven = scanner.nextDouble();
		
		System.out.print("Enter electricity used (kWh): ");
		double electricityUsed = scanner.nextDouble();
		
		System.out.print("Enter diet type (1 = Meat-heavy, 2 = Balanced, 3 = Vegetarian): ");
		int dietType = scanner.nextInt();
		
		double totalEmissions = CarbonCalculator.calculateTotal(milesDriven, electricityUsed, dietType);
		
		System.out.println("\nYour estimated daily carbon footprint: " + totalEmissions + " kg CO2");
		
		System.out.println("Suggestions for reducing your footprint:");
		Suggestions.getTips(milesDriven, electricityUsed, dietType);
		
		scanner.close();
		}
}
class CarbonCalculator {

	private static final double CARBON_PER_MILE = 0.404;
	private static final double CARBON_PER_KWH = 0.92;
	private static final double[] DIET_EMISSIONS = {7.2, 5.5, 3.8};
	
	public static double calculateTotal(double miles, double kWh, int diet) {
		double transportEmissions = miles * CARBON_PER_MILE;
		double electricityEmissions = kWh * CARBON_PER_KWH;
		double dietEmissions = DIET_EMISSIONS[diet-1];
		
		return transportEmissions + electricityEmissions + dietEmissions;
	}
}
class Suggestions {
	public static void getTips(double miles, double kWh, int diet) {
		if (miles > 20) {
			System.out.println("- Consider carpooling or using public transport.");
		} else if (miles > 5) {
			System.out.println("- Try biking or walking for short distances.");
			}
		if (kWh > 30) {
			System.out.println("- Reduce energy use by turning off unused lights.");
		}
		if (diet == 1) {
			System.out.println("- Consider reducing meat consumption to lower emissions.");
		}
	}
}			
class DataHandler {
	public static void saveData(double totalEmissions) {
		try {
			FileWriter writer = new FileWriter("carbon_footprint_log.txt", true);
			writer.write("Daily Carbon Footprint: " + totalEmissions + " kg CO2\n");
			writer.close();
			System.out.println("\nData saved successfully!");
		} catch (IOException e) {
			System.out.println("Error saving data.");
		}
	}
}			
			