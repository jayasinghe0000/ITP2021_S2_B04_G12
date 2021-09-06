package lk.sliit.hotel.dao.hrDAO;

import lk.sliit.hotel.entity.hr.SalarySettings;
import org.springframework.data.repository.CrudRepository;

public interface SalarySettingsDAO extends CrudRepository<SalarySettings,Integer> {

    Iterable<SalarySettings> findAllByOrderByIdDesc();

    SalarySettings findTopByOrderByIdDesc();
}
