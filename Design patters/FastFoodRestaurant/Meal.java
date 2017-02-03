package FastFoodRestaurant;
import java.util.ArrayList;
import java.util.List;

public class Meal {
List<Item> items = new ArrayList<Item>();

	private Meal (MealBuilder builder)
	{
		this.items=builder.getItems();
	}
	
	public void addItem(Item item)
	{
		items.add(item);
	}
	
	public float getCost()
	{
		float totalCost=0;
		
		for(Item tmp : items )
		{
			totalCost+=tmp.price();
		}
		return totalCost;
	}
	
	public void showItems()
	{
		for(Item tmp : items )
		{
			System.out.println("Product Name: "+tmp.name());
			System.out.println("Packing: "+tmp.pacikng().pack());
			System.out.println("Price: "+tmp.price());
		}
	}	
	
	//inner class- builder pattern
	public static class MealBuilder
	{
		List<Item> items = new ArrayList<Item>();
		
		public Meal build()
		{
			return new Meal(this);
		}		
		
		public MealBuilder prepareVegMeal()
		{
			addItem(new VegBurger());
			return this;
		}		
		
		public MealBuilder prepareNonVegMeal()
		{
			addItem(new ChickenBurger());
			return this;
		}
		//add new item to the list
		public MealBuilder addItem(Item item)
		{
			items.add(item);
			return this;
		}		

		public List<Item> getItems() {
			return items;
		}


	}

}
