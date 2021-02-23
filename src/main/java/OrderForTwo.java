import java.util.ArrayList;
import java.util.List;

public class OrderForTwo {
    private List<String> orders;

    public OrderForTwo() {
        orders = new ArrayList<>();
    }

    public void addValidOrder(String order){
        if(!order.contains(" for 2")){
            return;
        }
        addOrRemove(order);
    }

    private void addOrRemove(String order) {
        if(orders.contains(order)){
            orders.remove(order);
        }else{
            orders.add(order);
        }
    }

    public boolean allOrderForTwoAreTaken(){
        return orders.isEmpty();
    }

    public String getFirstOrderForTwo(){
        return orders.get(0);
    }
}
