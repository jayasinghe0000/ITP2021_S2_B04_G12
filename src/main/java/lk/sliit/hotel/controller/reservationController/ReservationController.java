package lk.sliit.hotel.controller.reservationController;

import lk.sliit.hotel.controller.SuperController;
import lk.sliit.hotel.service.custom.IndexLoginBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReservationController {

    @Autowired
    IndexLoginBO indexLoginBO;

    @GetMapping("/reservation")
    public String loginPage(Model model){
        model.addAttribute("loggerName", indexLoginBO.getEmployeeByIdNo(SuperController.idNo));
        return "reservation";
    }

    @GetMapping("/activityReports")
    public String reportPage(Model model) {
        model.addAttribute("loggerName", indexLoginBO.getEmployeeByIdNo(SuperController.idNo));
        return "activityReports";
    }
}

