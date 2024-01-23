package hello.core.singletone;

public class StatefuleService {
    private int price;

    public  void order(String name, int price) {
        System.out.println("name = " + name+" price = " + price) ;
        this.price = price;
    }

    public int getPrice(){
        return this.price;
    }

}
