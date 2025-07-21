package in.ineuron.entity;

public class FoodOrder {

	private int amount;
	private String foodName;

	public FoodOrder() {
		super();
	}

	public FoodOrder(int amount, String foodName) {
		super();
		this.amount = amount;
		this.foodName = foodName;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	@Override
	public String toString() {
		return "FoodOrder [amount=" + amount + ", foodName=" + foodName + "]";
	}

}
