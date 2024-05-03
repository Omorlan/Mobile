package ru.phonemarket.mobile.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.phonemarket.mobile.model.MobilePhone;
import ru.phonemarket.mobile.service.MobilePhoneService;

import java.util.List;

/**
 * This controller class handles HTTP requests related to mobile phones.
 */
@RestController
@RequestMapping("/mobilephones")
public class MobilePhoneController {
    private final MobilePhoneService mobilePhoneService;

    /**
     * Constructs a new MobilePhoneController with the specified MobilePhoneService.
     *
     * @param mobilePhoneService the service to be used by the controller
     */
    @Autowired
    public MobilePhoneController(MobilePhoneService mobilePhoneService) {
        this.mobilePhoneService = mobilePhoneService;
    }

    /**
     * Handles HTTP POST request to create a new mobile phone.
     *
     * @param mobilePhone the mobile phone to be created
     * @return the created mobile phone
     */
    @PostMapping
    public MobilePhone create(@Valid @RequestBody MobilePhone mobilePhone) {
        return mobilePhoneService.create(mobilePhone);
    }

    /**
     * Handles HTTP GET request to retrieve a mobile phone by its ID.
     *
     * @param id the ID of the mobile phone to retrieve
     * @return the mobile phone with the specified ID
     */
    @GetMapping("/{id}")
    public MobilePhone getById(@PathVariable Long id) {
        return mobilePhoneService.getMobilePhone(id);
    }

    /**
     * Handles HTTP GET request to retrieve all mobile phones.
     *
     * @return a list of all mobile phones
     */
    @GetMapping
    public List<MobilePhone> findAll() {
        return mobilePhoneService.findAll();
    }

    /**
     * Handles HTTP PUT request to update an existing mobile phone.
     *
     * @param mobilePhone the mobile phone to be updated
     * @return the updated mobile phone
     */
    @PutMapping
    public MobilePhone update(@Valid @RequestBody MobilePhone mobilePhone) {
        return mobilePhoneService.update(mobilePhone);
    }

    /**
     * Handles HTTP DELETE request to remove a mobile phone by its ID.
     *
     * @param id the ID of the mobile phone to remove
     */
    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        mobilePhoneService.remove(id);
    }

}
