package lk.sliit.hotel.service.custom.impl;

import lk.sliit.hotel.dto.kitchen.FoodItemDTO;
import lk.sliit.hotel.entity.kitchen.FoodItem;
import lk.sliit.hotel.service.custom.KitchenBO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KitchenBOImpl implements KitchenBO {

    @Override
    public List<FoodItemDTO> findFoodItems() {
            Iterable<FoodItem> foodItems = kitchenDAO.findAll();//call to crud repo
            List<FoodItemDTO> foodItemDTOList = new ArrayList<>();

            for (FoodItem item : foodItems) {
                foodItemDTOList.add(new FoodItemDTO(
                        item.getItemId(),
                        item.getName(),
                        item.getUnitePrice(),
                        item.getCategory(),
                        item.getSrc()));
            }
            return foodItemDTOList;
        }

    }

