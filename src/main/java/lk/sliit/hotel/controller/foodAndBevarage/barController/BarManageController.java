package lk.sliit.hotel.controller.foodAndBevarage.barController;

import lk.sliit.hotelManagement.controller.SuperController;
import lk.sliit.hotelManagement.service.custom.IndexLoginBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BarManageController {
    @Autowired
    IndexLoginBO indexLoginBO;


    @GetMapping("/bar")//Load Bar Dashboard
    public String loginPage(Model model) {
        model.addAttribute("loggerName", indexLoginBO.getEmployeeByIdNo(SuperController.idNo));
        return "bar";
    }
}
