package FastFoodRestaurant;

public class VegBurger extends Burger{

	public String name() {
		return "Veg Burger";
	}

	public float price() {
		return 20.5f;
	}

	public Packing pacikng() {
		return new Wrapper();
	}
}
