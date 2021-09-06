package lk.sliit.hotel.dao.hrDAO;

import lk.sliit.hotel.entity.hr.Accounts;
import org.springframework.data.repository.CrudRepository;

public interface AccountsDAO extends CrudRepository<Accounts,Integer> {
    Accounts findTopByOrderByAccountIdDesc();
}
