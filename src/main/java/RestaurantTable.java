import java.util.*;
import java.util.stream.Collectors;

public class RestaurantTable {
    private int id;
    private int sizeOfTable;
    private Map<String, String> orders;
    private OrderForTwo orderForTwo;
    private String lastFood;

    public RestaurantTable(int sizeOfTable, int id) {
        this.sizeOfTable = sizeOfTable;
        orderForTwo = new OrderForTwo();
        orders = new LinkedHashMap<>();
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void addOrder(String name, String food) {
        if(food.equals(" Same")){
            food = lastFood;
        }
        orderForTwo.addValidOrder(food);
        orders.put(name, food);
        lastFood = food;
    }

    public boolean everybodyOrdered(){
        return orders.size() == sizeOfTable;
    }

    public boolean allOrderForTwoAreTaken(){
        return orderForTwo.allOrderForTwoAreTaken();
    }

    public String firstOrderOfTwo(){
        return orderForTwo.getFirstOrderForTwo();
    }

    public String getListOfOrders(){
        return orders.values().stream().map(String::trim).collect(Collectors.joining(", "));
    }
}
