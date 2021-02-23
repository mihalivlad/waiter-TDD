import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class Restaurant {
    List<RestaurantTable> restaurantTableList;
    private int tableCount;

    public Restaurant() {
        restaurantTableList = new ArrayList<RestaurantTable>();
        tableCount = 0;
    }

    public int initTable(int sizeOfTable) {
        RestaurantTable restaurantTable = new RestaurantTable(sizeOfTable, tableCount++);
        restaurantTableList.add(restaurantTable);
        return restaurantTable.getId();
    }

    public void customerSays(final int tableId, String message) {
        RestaurantTable restaurantTable = getRestaurantTable(tableId);
        List<String> messageSplit = getSplitCustomerText(message);
        restaurantTable.addOrder(messageSplit.get(0), messageSplit.get(1));
    }

    private List<String> getSplitCustomerText(String message) {
        List<String> messageSplit = Arrays.asList(message.split(":"));
        if(messageSplit.size() != 2){
            throw new RuntimeException("wrong order");
        }
        return messageSplit;
    }

    private RestaurantTable getRestaurantTable(int tableId) {
        Optional<RestaurantTable> restaurantTable = restaurantTableList.stream()
                .filter(table -> table.getId() == tableId)
                .findAny();
        if(!restaurantTable.isPresent()){
            throw new RuntimeException("wrong tableId");
        }
        return restaurantTable.get();
    }

    public String createOrder(int tableId) {
        RestaurantTable restaurantTable = getRestaurantTable(tableId);
        if(!restaurantTable.everybodyOrdered()){
            return "MISSING 2";
        }
        if(!restaurantTable.allOrderForTwoAreTaken()){
            return "MISSING 1 for"+restaurantTable.firstOrderOfTwo();
        }
        return restaurantTable.getListOfOrders();
    }
}
