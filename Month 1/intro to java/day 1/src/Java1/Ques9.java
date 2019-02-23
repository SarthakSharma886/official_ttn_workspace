package Java1;

enum Rent{
    Delhi(1000),
    Chennai(2000),
    Bangalore(3000),
    Hyderabad(4000);

    private int price;

    Rent(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

}
public class Ques9 {

    public static void main(String[] args) {
        System.out.println(Rent.values().toString());
        for(Rent house : Rent.values()){
            System.out.println("House name : "+house+"\nRent : "+house.getPrice());
        }

    }
}