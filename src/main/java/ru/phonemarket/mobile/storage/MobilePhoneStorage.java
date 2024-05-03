package ru.phonemarket.mobile.storage;

import org.springframework.stereotype.Repository;
import ru.phonemarket.mobile.model.MobilePhone;

import java.util.List;

@Repository
public interface MobilePhoneStorage {
    MobilePhone create(MobilePhone mobilePhone);

    MobilePhone update(MobilePhone mobilePhone);

    void remove(Long id);

    List<MobilePhone> findAll();

    MobilePhone getById(Long id);
}
