package lk.sliit.hotel.service.custom;

import lk.sliit.hotel.dto.kitchen.FoodItemDTO;
import lk.sliit.hotel.service.SuperBO;

import java.sql.Date;
import java.util.List;

public interface KitchenBO extends SuperBO {

    List<FoodItemDTO> findFoodItems();


}
