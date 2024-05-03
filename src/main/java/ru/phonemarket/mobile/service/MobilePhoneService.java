package ru.phonemarket.mobile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.phonemarket.mobile.model.MobilePhone;
import ru.phonemarket.mobile.storage.MobilePhoneStorage;

import java.util.List;

@Service
public class MobilePhoneService {

    private final MobilePhoneStorage inMemoryMobilePhoneStorage;

    @Autowired
    public MobilePhoneService(MobilePhoneStorage inMemoryMobilePhoneStorage) {
        this.inMemoryMobilePhoneStorage = inMemoryMobilePhoneStorage;
    }

    public MobilePhone create(MobilePhone mobilePhone) {
        return inMemoryMobilePhoneStorage.create(mobilePhone);
    }

    public MobilePhone getMobilePhone(Long id) {
        return inMemoryMobilePhoneStorage.getById(id);
    }

    public List<MobilePhone> findAll() {
        return inMemoryMobilePhoneStorage.findAll();
    }

    public MobilePhone update(MobilePhone mobilePhone) {
        return inMemoryMobilePhoneStorage.update(mobilePhone);
    }

    public void remove(Long id) {
        inMemoryMobilePhoneStorage.remove(id);
    }
}
