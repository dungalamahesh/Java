package FastFoodRestaurant;

public class Pepsi extends ColdDrink{

	public String name() {
		return "Pepsi Cola";
	}

	public Packing pacikng() {
		return new Bottle();
	}

	public float price() {
		return 4.15f;
	}
}
