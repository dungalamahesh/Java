package FastFoodRestaurant;

public abstract class ColdDrink implements Item{
	/**Must TODO (new pack)**/
	public Packing packing(){
		return new Bottle();
	}	
	//Price of the item Burger
	public abstract float price();
}
