package FastFoodRestaurant;

public abstract class Burger implements Item {
	Wrapper wrapper;
	public Burger(){
		wrapper = new Wrapper();
	}
	/**Must TODO (new pack)**/
	public Packing packing(){
		return wrapper; // return the Wrapper
	}
	
	//Price of the item Burger
	public abstract float price();
}
