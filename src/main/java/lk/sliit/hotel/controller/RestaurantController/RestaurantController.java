package lk.sliit.hotel.controller.RestaurantController;

import lk.sliit.hotel.controller.SuperController;
import lk.sliit.hotel.dto.kitchen.FoodItemDTO;
import lk.sliit.hotel.service.custom.IndexLoginBO;
import lk.sliit.hotel.service.custom.RestaurantBO;
import lk.sliit.hotelManagement.controller.SuperController;
import lk.sliit.hotelManagement.dto.kitchen.FoodItemDTO;
import lk.sliit.hotelManagement.dto.restaurant.restaurantCounterOrder.RestaurantCounterOrderDTO;
import lk.sliit.hotelManagement.dto.restaurant.restaurantCounterOrder.RestaurantCounterOrderDetailDTO;
import lk.sliit.hotelManagement.dto.restaurant.restaurantOnlineOrder.RestaurantOnlineOrderDTO;
import lk.sliit.hotelManagement.dto.restaurant.restaurantOnlineTable.OnlineTableReservationDTO;
import lk.sliit.hotelManagement.service.custom.IndexLoginBO;
import lk.sliit.hotelManagement.service.custom.KitchenBO;
import lk.sliit.hotelManagement.service.custom.RestaurantBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RestaurantController {

    @Autowired
    KitchenBO kitchenBO;
    @Autowired
    RestaurantBO restaurantBO;
    @Autowired
    IndexLoginBO indexLoginBO;




    //load restaurant dashboard
    @GetMapping("/restaurant")//Restaurant Dashboard
    public String loginPage(Model model) {
        model.addAttribute("loggerName", indexLoginBO.getEmployeeByIdNo(SuperController.idNo));
        return "restaurant";
    }

    @GetMapping("/restaurantOrder")//Load Restaurant Order Form
    public String restaurantOrders(Model model, HttpServletRequest request) {
        model.addAttribute("loggerName", indexLoginBO.getEmployeeByIdNo(SuperController.idNo));


        List<FoodItemDTO> p1 = kitchenBO.findFoodItems();//Find Food Items
        if (p1.isEmpty()) {
            request.setAttribute("loginError", "Not Any Fond Items" +
                    " Please Add Food Items ");
        }
        model.addAttribute("loadInventoryRestaurantTable", p1);//load Data to table
        return "resturantOrder";
    }

    @PostMapping("/saveFoodItem")
    public String addNew(Model model, @ModelAttribute FoodItemDTO foodItemDTO) {
        // alertMsg = null;
        //model.addAttribute("loggerName", indexLoginBO.getEmployeeByIdNo(SuperController.idNo));
        // model.addAttribute("alert",alertMsg);

        try {
            FoodItemDTO foodItemDTO2 = foodItemService.findHighestId();
            FoodItemDTO foodItemDTO1 = null;
            try {
                foodItemDTO1 = foodItemService.findFoodItemById(foodItemDTO.getItemId());
            }catch (NullPointerException d){
                int maxId = (foodItemDTO2.getItemId());
                if (foodItemDTO.getItemId()==(maxId)) {
                    foodItemDTO.setItemId((maxId));
                } else {
                    maxId++;
                    foodItemDTO.setItemId((maxId));
                }
            }
        } catch (NullPointerException e){
            foodItemDTO.setItemId(1);
        }

        //model.addAttribute("loggerName", indexLoginBO.getEmployeeByIdNo(SuperController.idNo));
        foodItemService.saveFoodItem(foodItemDTO);
        return "redirect:/restaurantOrder";
    }

    @PostMapping("invoice")//Print Invoice
    public String loadInvoicePage(@ModelAttribute RestaurantCounterOrderDTO restaurantCounterOrderDTO, Model model, HttpServletRequest request) {
        //model.addAttribute("loggerName", indexLoginBO.getEmployeeByIdNo(SuperController.idNo));

        try { //
            //restaurantCounterOrderDTO.setCustomerId(SuperController.idNo);
            RestaurantCounterOrderDTO top = restaurantService.findTopByOrderByRestIdDesc();//find Highest Id to Save Order
            int x = (top.getOrderId()) + 1;
            restaurantCounterOrderDTO.setOrderId((x));
        } catch (NullPointerException e) {
            restaurantCounterOrderDTO.setOrderId((1));//Set Id as 1 when Initial Round
        }
        try {
            restaurantService.saveRestaurantOrder(restaurantCounterOrderDTO);

            java.util.List<RestaurantCounterOrderDetailDTO> list = new ArrayList<>();
            String arr = restaurantCounterOrderDTO.getDataValue();
            String yo[] = arr.split(" ");
            int count = 0;
            RestaurantCounterOrderDetailDTO itm = new RestaurantCounterOrderDetailDTO();
            for (String str : yo) {//Read String and add to list
                if (count == 0) {
                    itm = new RestaurantCounterOrderDetailDTO();
                    itm.setFoodItem(Integer.parseInt(str));
                    count++;

                } else if (count == 1) {
                    itm.setUnitePrice(Double.parseDouble(str));
                    count++;

                } else if (count == 2) {
                    itm.setQuantity(Double.parseDouble(str));
                    list.add(itm);
                    count = 0;
                }
            }

            for (RestaurantCounterOrderDetailDTO d : list) {
                FoodItemDTO f = foodItemService.findFoodItemById(d.getFoodItem());
                d.setName(f.getItemName());
            }

            model.addAttribute("listCounterOrders", restaurantCounterOrderDTO.getOrderId());
            model.addAttribute("listCounterOrderDetails", list);//Load Data to Payment

        } catch (Exception e) {
            return "redirect:/restaurantOrder";
        }
        return "invoice";
    }


    //report
    @GetMapping("/RestaurantReport")
    public String loadKitchenReport(Model model) {
        //alertMsg = null;
        // model.addAttribute("alert", alertMsg);
        // model.addAttribute("loggerName", indexLoginBO.getEmployeeByIdNo(SuperController.idNo));

        List<RestaurantFoodOrderDTO> allFinishedOrders = restaurantService.findReportData(new java.util.Date());
        List<RestaurantFoodItemDTO> onlineItems = new ArrayList<>();
        List<RestaurantFoodItemDTO> counterItems = new ArrayList<>();
        List<RestaurantFoodItemDTO> finalList = new ArrayList<>();

        //model variables for calculations
        int totalItemsSold = 0;
        int totalOnlineItemsSold = 0;
        int totalCounterItemsSold = 0;
        double totalOnlineIncome = 0;
        double totalCounterIncome = 0;
        double totalIncome = 0;

        if (!allFinishedOrders.isEmpty()){
            for (RestaurantFoodOrderDTO order: allFinishedOrders){

                System.out.println("======================================================\n\n");
                System.out.println(order.getType()+"\n");
                for (RestaurantFoodItemDTO item: order.getFoodItems()){
                    System.out.println("ID: "+item.getFoodItemId());
                    System.out.println("Name: "+item.getFoodName());
                    System.out.println("Quantity: "+item.getQuantity());
                    System.out.println("Price"+item.getPrice());
                    System.out.println("Total price: "+item.getTotalPrice());

                }

                System.out.println("======================================================\n\n");


                //////////////////////////////////////////////


                if (order.getType().equals(KitchenUtil.onlineType)){

                    onlineItems = order.getFoodItems();

                    //calc total item sold
                    if (!onlineItems.isEmpty()){

                        for (RestaurantFoodItemDTO itemDTO : onlineItems){
                            totalOnlineItemsSold += itemDTO.getQuantity();
                            totalOnlineIncome += itemDTO.getTotalPrice();
                        }

                        //set selling rates
                        for (RestaurantFoodItemDTO itemDTO : onlineItems){
                            itemDTO.setSellingRateOnline(Math.round(((itemDTO.getQuantity() / totalOnlineItemsSold) * 100)));
                        }

                    }


                } else if (order.getType().equals(KitchenUtil.counterType)){

                    counterItems = order.getFoodItems();

                    //calc total item sold
                    if (!counterItems.isEmpty()){

                        for (RestaurantFoodItemDTO itemDTO : counterItems){
                            totalCounterItemsSold += itemDTO.getQuantity();
                            totalCounterIncome += itemDTO.getTotalPrice();
                        }

                        //set selling rates
                        for (RestaurantFoodItemDTO itemDTO : counterItems){
                            itemDTO.setSellingRateCounter(Math.round((itemDTO.getQuantity() / totalCounterItemsSold) * 100));
                        }
                    }
                }
            }

            totalIncome = totalCounterIncome + totalOnlineIncome;
            totalItemsSold = totalCounterItemsSold + totalOnlineItemsSold;

            if (!onlineItems.isEmpty()){
                finalList.addAll(onlineItems);

                if (!counterItems.isEmpty()){
                    for (RestaurantFoodItemDTO counterItem: counterItems){
                        for (RestaurantFoodItemDTO list:finalList){
                            if (counterItem.getFoodItemId() == list.getFoodItemId()){
                                list.setSellingRateCounter(counterItem.getSellingRateCounter());
                                list.setQuantity(list.getQuantity() + counterItem.getQuantity());
                            }
                        }
                    }
                } else {
                    for (RestaurantFoodItemDTO itemDTO:finalList){
                        itemDTO.setSellingRateCounter(0);
                    }
                }
            } else {
                finalList.addAll(counterItems);

                for (RestaurantFoodItemDTO itemDTO:finalList){
                    itemDTO.setSellingRateOnline(0);
                }
            }

        }



        model.addAttribute("table", finalList);
        model.addAttribute("totalItemsSold",totalItemsSold);
        model.addAttribute("totalOnline", totalOnlineItemsSold);
        model.addAttribute("totalCounter", totalCounterItemsSold);
        model.addAttribute("totalCounterIncome", totalCounterIncome);
        model.addAttribute("totalOnlineIncome", totalOnlineIncome);
        model.addAttribute("totalIncome", totalIncome);

        return "RestaurantReport";
    }

}
