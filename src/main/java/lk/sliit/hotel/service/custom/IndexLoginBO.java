package lk.sliit.hotel.service.custom;

import lk.sliit.hotel.service.SuperBO;

import java.util.List;

public interface IndexLoginBO extends SuperBO {
    EmployeeDTO findByIdNoAndPassword(int idNo, String password);

    EmployeeDTO getEmployeeByIdNo(int idNo);

    List<NoticeDTO> findResentNoticeDesc();

}
