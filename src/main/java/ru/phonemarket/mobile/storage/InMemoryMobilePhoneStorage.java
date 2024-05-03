package ru.phonemarket.mobile.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.phonemarket.mobile.exception.NotFoundException;
import ru.phonemarket.mobile.model.MobilePhone;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * This class represents an in-memory storage for mobile phones.
 * It stores mobile phones in a HashMap.
 */
@Slf4j
@Component
public class InMemoryMobilePhoneStorage implements MobilePhoneStorage {
    private final Map<Long, MobilePhone> phones;
    private Long phoneId;

    /**
     * Constructs a new InMemoryMobilePhoneStorage with an empty HashMap.
     */
    public InMemoryMobilePhoneStorage() {
        this.phones = new HashMap<>();
        this.phoneId = 0L;
    }

    /**
     * Generates and returns the next available ID.
     *
     * @return the next available ID
     */
    private Long getNextId() {
        return phoneId++;
    }

    /**
     * Creates a new mobile phone and adds it to the storage.
     *
     * @param mobilePhone the mobile phone to be created
     * @return the created mobile phone
     */
    public MobilePhone create(MobilePhone mobilePhone) {
        log.info("Creating new mobile phone");
        Long id = getNextId();
        mobilePhone.setId(id);
        log.info(String.format("Set id = %s to new mobile phone", id));
        phones.put(id, mobilePhone);
        return mobilePhone;
    }

    /**
     * Retrieves all mobile phones from the storage.
     *
     * @return a list of all mobile phones
     */
    public List<MobilePhone> findAll() {
        log.info("Retrieving all mobile phones");
        return phones.values().stream().toList();
    }

    /**
     * Retrieves a mobile phone by its ID.
     *
     * @param id the ID of the mobile phone to retrieve
     * @return the mobile phone with the specified ID
     * @throws NotFoundException if the mobile phone with the specified ID is not found
     */
    public MobilePhone getById(Long id) {
        if (phones.containsKey(id)) {
            return phones.get(id);
        } else {
            throw new NotFoundException(String.format("Mobile phone with id = %s not found", id));
        }
    }

    /**
     * Updates an existing mobile phone in the storage.
     *
     * @param mobilePhone the mobile phone to be updated
     * @return the updated mobile phone
     * @throws NotFoundException if the mobile phone with the specified ID is not found
     */
    public MobilePhone update(MobilePhone mobilePhone) {
        Long id = mobilePhone.getId();
        log.info("Updating film with ID {}", id);
        if (phones.containsKey(id)) {
            phones.put(id, mobilePhone);
            return mobilePhone;
        } else {
            throw new NotFoundException(String.format("Mobile phone with id = %s not found", id));
        }
    }

    /**
     * Removes a mobile phone from the storage by its ID.
     *
     * @param id the ID of the mobile phone to remove
     * @throws NotFoundException if the mobile phone with the specified ID is not found
     */
    public void remove(Long id) {
        if (phones.containsKey(id)) {
            phones.remove(id);
        } else {
            throw new NotFoundException(String.format("Mobile phone with id = %s not found", id));
        }
    }
}
