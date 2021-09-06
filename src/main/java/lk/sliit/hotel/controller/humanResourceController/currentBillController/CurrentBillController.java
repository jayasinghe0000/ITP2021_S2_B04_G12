package lk.sliit.hotel.controller.humanResourceController.currentBillController;

import lk.sliit.hotel.controller.SuperController;
import lk.sliit.hotel.dto.hr.CurrentBillDTO;
import lk.sliit.hotel.service.custom.CurrentBO;
import lk.sliit.hotel.service.custom.HumanResourceBO;
import lk.sliit.hotel.service.custom.IndexLoginBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class CurrentBillController {
    // automate the object creation and connect with the relevant interfaces
    @Autowired
    IndexLoginBO indexLoginBO;

    @Autowired
    HumanResourceBO humanResourceBO;

    @Autowired
    CurrentBO currentBO;

    @GetMapping ("/currentBill")//load Current bill page and data
    public ModelAndView loadCurrentBillDetails(Model model) {
        ModelAndView modelAndView = new ModelAndView("currentBill");
        model.addAttribute("loggerName", indexLoginBO.getEmployeeByIdNo(SuperController.idNo));
        List<CurrentBillDTO> cbDTOList = currentBO.findAllCurrentBills(); // find available data
        modelAndView.addObject("loadCBTable", cbDTOList);
        return modelAndView;
    }

    @PostMapping("saveCurrentBill")//save Current bill
    public String saveCurrentBillForm(@ModelAttribute CurrentBillDTO currentBillDTO){
        try {

            CurrentBillDTO current = currentBO.findHighestCurrentBillId(); // get highest bill id
            CurrentBillDTO currentBillDTO1 = null; //set dto object to null
            try {
                currentBillDTO1 = currentBO.findById(currentBillDTO.getBillId()); // get the next bill id

            }catch (NullPointerException d){
                int maxId = (current.getBillId()); // get available bill id

                if (currentBillDTO.getBillId()==(maxId)) {//If true Update Bill Id
                    currentBillDTO.setBillId((maxId));
                } else {//create new Current bill
                    maxId++;
                    currentBillDTO.setBillId((maxId));
                }
            }

        } catch (NullPointerException e){
            currentBillDTO.setBillId(1); // set bill id as 1
        }
        currentBO.saveCurrentBill(currentBillDTO);//save
        return "redirect:/currentBill";
    }

    // delete current bill
    @RequestMapping(value = "deleteCurrentBill/{billId}")
    public void deleteEmployee(@PathVariable("billId") int billId, HttpServletResponse response) throws IOException {
            currentBO.deleteCurrentBill(billId);
            response.sendRedirect("/currentBill");
    }

}
