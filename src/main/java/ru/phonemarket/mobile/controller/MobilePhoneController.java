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

@RestController
@RequestMapping("/mobilephones")
public class MobilePhoneController {
    private final MobilePhoneService mobilePhoneService;

    @Autowired
    public MobilePhoneController(MobilePhoneService mobilePhoneService) {
        this.mobilePhoneService = mobilePhoneService;
    }

    @PostMapping
    public MobilePhone create(@Valid @RequestBody MobilePhone mobilePhone) {
        return mobilePhoneService.create(mobilePhone);
    }

    @GetMapping("/{id}")
    public MobilePhone getById(@PathVariable Long id) {
        return mobilePhoneService.getMobilePhone(id);
    }

    @GetMapping
    public List<MobilePhone> findAll() {
        return mobilePhoneService.findAll();
    }

    @PutMapping
    public MobilePhone update(@Valid @RequestBody MobilePhone mobilePhone) {
        return mobilePhoneService.update(mobilePhone);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        mobilePhoneService.remove(id);
    }

}
