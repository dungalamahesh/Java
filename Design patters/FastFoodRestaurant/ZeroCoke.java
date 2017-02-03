package FastFoodRestaurant;

public class ZeroCoke extends ColdDrink{

	public String name() {
		return "Zero Coke";
	}

	public Packing pacikng() {
		return new Bottle();
	}

	public float price() {
		return 6.15f;
	}
	
}
