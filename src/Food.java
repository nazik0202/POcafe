public class Food extends Item {
    private boolean isVegan;
    private boolean isSpicy;

    public Food(double price, String name, boolean isVegan, boolean isSpicy) {
        super(price, name);
        this.isVegan = isVegan;
        this.isSpicy = isSpicy;
    }

    @Override
    public String toString() {
        return "Food{" +
                "price: "+ this.getPrice()+
                " name: "+ this.getName()+
                " isVegan=" + isVegan +
                ", isSpicy=" + isSpicy +
                '}';
    }
}
