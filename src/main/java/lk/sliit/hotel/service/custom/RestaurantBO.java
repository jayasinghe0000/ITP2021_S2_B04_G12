package lk.sliit.hotel.service.custom;

import lk.sliit.hotel.dto.kitchen.FoodItemDTO;
import lk.sliit.hotel.dto.kitchen.RestaurantFoodOrderDTO;
import lk.sliit.hotel.dto.restaurant.RestaurantTableDTO;
import lk.sliit.hotel.dto.restaurant.restaurantCounterOrder.RestaurantCounterOrderDTO;
import lk.sliit.hotel.dto.restaurant.restaurantCounterTable.CounterTableReservationDTO;
import lk.sliit.hotel.dto.restaurant.restaurantOnlineOrder.RestaurantOnlineOrderDTO;
import lk.sliit.hotel.dto.restaurant.restaurantOnlineTable.OnlineTableReservationDTO;

import java.util.Date;
import java.util.List;

public interface RestaurantBO {
    RestaurantCounterOrderDTO findTopByOrderByRestIdDesc();

    void saveRestaurantOrder(RestaurantCounterOrderDTO restaurantCounterOrderDTO);

    List<FoodItemDTO> findAllFoodItems(String restaurant);

    List<RestaurantTableDTO> findAllTable();

    //List<RestaurantTableDTO> findAllTableDateEqual(Date date, Date startTime, Date endTime);

    RestaurantOnlineOrderDTO findHighestOnlineOrderId();

    void saveOnlineOrder(RestaurantOnlineOrderDTO onlineOrderDTO);


    List<RestaurantTableDTO> getAviTables(Date date, Date startTime, Date endTime);

    RestaurantTableDTO findHighestTableId();

    void saveTable(RestaurantTableDTO restaurantTableDTO);

   /* List<RestaurantTableDTO> findTables();*/

    void deleteTable(int tableId);

    RestaurantTableDTO findTableById(int tableId);

    OnlineTableReservationDTO findHighestOnlineTableId();
    CounterTableReservationDTO findHighestCounterTableId();

    void saveOnlineTableId(OnlineTableReservationDTO onlineOrderDTO);

    List<CounterTableReservationDTO> getBookedTables();

    void saveCounterTableId(CounterTableReservationDTO onlineOrderDTO);

    List<OnlineTableReservationDTO> findTablesOnline();

    List<RestaurantOnlineOrderDTO> findOrderOnline();

    //restaurant report
    List<RestaurantFoodOrderDTO> findResReportData(java.util.Date date);


}
