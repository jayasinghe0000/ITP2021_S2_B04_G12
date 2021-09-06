package lk.sliit.hotel.service.custom.impl;

import lk.sliit.hotel.dao.hrDAO.CurrentBillDAO;
import lk.sliit.hotel.dto.hr.CurrentBillDTO;
import lk.sliit.hotel.entity.hr.CurrentBill;
import lk.sliit.hotel.service.custom.CurrentBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CurrentBOImpl implements CurrentBO {

    @Autowired
    CurrentBillDAO currentBillDAO;


    @Override
    public void saveCurrentBill(CurrentBillDTO currentBillDTO) {
        currentBillDAO.save(new CurrentBill(
                currentBillDTO.getBillId(),
                currentBillDTO.getAmount(),
                currentBillDTO.getDate()
        ));
    }

    @Override
    @Transactional (readOnly = true)
    public List<CurrentBillDTO> findAllCurrentBills() {
        Iterable<CurrentBill> currentBillDetails = currentBillDAO.findAll();
        List<CurrentBillDTO> currentBillDTOS = new ArrayList<>();
        for (CurrentBill cb: currentBillDetails) {
            currentBillDTOS.add(new CurrentBillDTO(
                    cb.getBillId(),
                    cb.getAmount(),
                    cb.getDate()
            ));
        }
        return currentBillDTOS;
    }

    @Override
    public CurrentBillDTO findById(int billId) {
        CurrentBill currentBill = currentBillDAO.findOne(billId);
        return new CurrentBillDTO(
                currentBill.getBillId(),
                currentBill.getAmount(),
                currentBill.getDate()
        );
    }

    @Override
    public void deleteCurrentBill(int billId) {
        currentBillDAO.delete(billId);
    }

    @Override
    public CurrentBillDTO findHighestCurrentBillId() {
        CurrentBill lastCurrentBillId = null;
        try{
            lastCurrentBillId = currentBillDAO.findTopByOrderByBillIdDesc();

        }catch(Exception e){

        } return new CurrentBillDTO(lastCurrentBillId.getBillId());

    }
}
