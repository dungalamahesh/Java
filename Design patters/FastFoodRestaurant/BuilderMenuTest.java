package FastFoodRestaurant;

import javax.swing.JOptionPane;

public class BuilderMenuTest {

	public static void main(String[] args) {
		ZeroCoke cola= new ZeroCoke();
		Pepsi pep= new Pepsi();
		Burger beefBurger = new ChickenBurger ();
		
		Meal newMeal= new Meal.MealBuilder().prepareVegMeal().addItem(beefBurger).build();
		newMeal.showItems();
	}

}
