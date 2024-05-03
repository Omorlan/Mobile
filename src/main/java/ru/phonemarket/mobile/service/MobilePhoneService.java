package ru.phonemarket.mobile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.phonemarket.mobile.model.MobilePhone;
import ru.phonemarket.mobile.storage.MobilePhoneStorage;

import java.util.List;

/**
 * This service class provides operations related to mobile phones.
 */
@Service
public class MobilePhoneService {

    private final MobilePhoneStorage inMemoryMobilePhoneStorage;

    /**
     * Constructs a new MobilePhoneService with the specified MobilePhoneStorage.
     *
     * @param inMemoryMobilePhoneStorage the storage to be used by the service
     */
    @Autowired
    public MobilePhoneService(MobilePhoneStorage inMemoryMobilePhoneStorage) {
        this.inMemoryMobilePhoneStorage = inMemoryMobilePhoneStorage;
    }

    /**
     * Creates a new mobile phone.
     *
     * @param mobilePhone the mobile phone to be created
     * @return the created mobile phone
     */
    public MobilePhone create(MobilePhone mobilePhone) {
        return inMemoryMobilePhoneStorage.create(mobilePhone);
    }

    /**
     * Retrieves a mobile phone by its ID.
     *
     * @param id the ID of the mobile phone to retrieve
     * @return the mobile phone with the specified ID
     */
    public MobilePhone getMobilePhone(Long id) {
        return inMemoryMobilePhoneStorage.getById(id);
    }

    /**
     * Retrieves all mobile phones.
     *
     * @return a list of all mobile phones
     */
    public List<MobilePhone> findAll() {
        return inMemoryMobilePhoneStorage.findAll();
    }

    /**
     * Updates an existing mobile phone.
     *
     * @param mobilePhone the mobile phone to be updated
     * @return the updated mobile phone
     */
    public MobilePhone update(MobilePhone mobilePhone) {
        return inMemoryMobilePhoneStorage.update(mobilePhone);
    }

    /**
     * Removes a mobile phone by its ID.
     *
     * @param id the ID of the mobile phone to remove
     */
    public void remove(Long id) {
        inMemoryMobilePhoneStorage.remove(id);
    }
}
