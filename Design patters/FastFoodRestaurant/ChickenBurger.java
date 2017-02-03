package FastFoodRestaurant;

public class ChickenBurger extends Burger{
	
	public String name() {
		return "Chiken Burger";
	}

	public float price() {
		return 50.5f;
	}

	public Packing pacikng() {
		return new Wrapper();
	}
}
