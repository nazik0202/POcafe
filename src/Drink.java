public class Drink extends Item{
    private boolean isAlcohol;
    private int volume;

    public Drink(double price, String name, boolean isAlcohol, int volume) {
        super(price, name);
        this.isAlcohol = isAlcohol;
        this.volume = volume;
    }

    @Override
    public String toString() {
        String a;
        if(isAlcohol){
            a = "18+";
        }
        else {
            a = "";
        }
        return  this.getName()+
                " price: "+ this.getPrice()+
                ", volume: " + volume + " "+
                a;
    }
}
