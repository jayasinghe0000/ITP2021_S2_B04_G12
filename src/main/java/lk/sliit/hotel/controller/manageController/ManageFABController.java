package lk.sliit.hotel.controller.manageController;

import lk.sliit.hotelManagement.controller.SuperController;
import lk.sliit.hotelManagement.service.custom.IndexLoginBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManageFABController {
    @Autowired
    IndexLoginBO indexLoginBO;
    @GetMapping("/manageF&B")
    public String loginPage(Model model){
        model.addAttribute("loggerName", indexLoginBO.getEmployeeByIdNo(SuperController.idNo));
        return "manageF&B";
    }
}
