package lk.sliit.hotel.controller.foodAndBevarage.Restaurant;

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
    IndexLoginBO indexLoginBO;
    @Autowired
    RestaurantBO restaurantBO;
    @Autowired
    KitchenBO kitchenBO;

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
        return "restaurantOrder";
    }

    @PostMapping("invoice")//Print Invoice
    public String loadInvoicePage(@ModelAttribute RestaurantCounterOrderDTO restaurantCounterOrderDTO, Model model, HttpServletRequest request) {
        model.addAttribute("loggerName", indexLoginBO.getEmployeeByIdNo(SuperController.idNo));

        try { //
            restaurantCounterOrderDTO.setCustomerId(SuperController.idNo);
            RestaurantCounterOrderDTO top = restaurantBO.findTopByOrderByRestIdDesc();//find Highest Id to Save Order
            int x = (top.getOrderId()) + 1;
            restaurantCounterOrderDTO.setOrderId((x));
        } catch (NullPointerException e) {
            restaurantCounterOrderDTO.setOrderId((1));//Set Id as 1 when Initial Round
        }
        try {
            restaurantBO.saveRestaurantOrder(restaurantCounterOrderDTO);

            List<RestaurantCounterOrderDetailDTO> list = new ArrayList<>();
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
                FoodItemDTO f = kitchenBO.findFoodItemById(d.getFoodItem());
                d.setName(f.getItemName());
            }

            model.addAttribute("listCounterOrders", restaurantCounterOrderDTO.getOrderId());
            model.addAttribute("listCounterOrderDetails", list);//Load Data to Payment

        } catch (Exception e) {
            return "redirect:/restaurantOrder";
        }
        return "invoice";
    }

    @GetMapping("/invoice")
    public String restaurant(Model model) {
        model.addAttribute("loggerName", indexLoginBO.getEmployeeByIdNo(SuperController.idNo));
        return "invoice";
    }

    @GetMapping("/restaurantManage")//Load Online Orders and Online Table
    public String restaurantManage(Model model) {
        model.addAttribute("loggerName", indexLoginBO.getEmployeeByIdNo(SuperController.idNo));

        List<RestaurantOnlineOrderDTO> tableList = restaurantBO.findOrderOnline();//Online Orders with in one month
        model.addAttribute("listAllOnlineOrders", tableList);
        List<OnlineTableReservationDTO> tableList1 = restaurantBO.findTablesOnline();//Online Table in one month
        model.addAttribute("onlineTableReservation", tableList1);
        return "restaurantManage";
    }
    @GetMapping("/restaurantBill")
    public String restaurantBill(Model model) {
        model.addAttribute("loggerName", indexLoginBO.getEmployeeByIdNo(SuperController.idNo));
        return "restaurantBill";
    }


}
