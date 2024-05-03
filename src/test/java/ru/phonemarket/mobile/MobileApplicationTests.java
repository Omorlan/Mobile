package ru.phonemarket.mobile;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import ru.phonemarket.mobile.controller.MobilePhoneController;
import ru.phonemarket.mobile.exception.NotFoundException;
import ru.phonemarket.mobile.model.MobilePhone;
import ru.phonemarket.mobile.service.MobilePhoneService;
import ru.phonemarket.mobile.storage.InMemoryMobilePhoneStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class MobileApplicationTests {
    private InMemoryMobilePhoneStorage storage;
    private MobilePhoneService phoneService;
    private MobilePhoneController controller;
    private Validator validator;

    @BeforeEach
    void setUp() {
        storage = new InMemoryMobilePhoneStorage();
        phoneService = new MobilePhoneService(storage);
        controller = new MobilePhoneController(phoneService);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AllArgsConstructor
    static class ExpectedViolation {
        private String propertyPath;
        private String message;
    }

    void genDefaultPhone(int count) {
        Stream.generate(() -> MobilePhone.builder()
                        .id(null)
                        .brand("Samsung")
                        .model("Galaxy A54")
                        .memorySize(256)
                        .accumulatorCapacity(5000)
                        .numberOfCameras(3)
                        .build())
                .limit(count)
                .forEach(controller::create);
    }

    MobilePhone genSpecPhone(String brand, String model, Integer memorySize, Integer accumulatorCapacity, Integer numberOfCameras) {
        MobilePhone phone = MobilePhone.builder()
                .id(null)
                .brand(brand)
                .model(model)
                .memorySize(memorySize)
                .accumulatorCapacity(accumulatorCapacity)
                .numberOfCameras(numberOfCameras)
                .build();
        return phone;
    }

    @Nested
    class CorrectDataTests {
        @Test
        @DisplayName("The findAll method should return List with all phones")
        void findAllShouldReturnAllPhonesList() {
            genDefaultPhone(2);
            List<MobilePhone> phoneList = controller.findAll();
            assertNotNull(phoneList, "List is empty");
            assertEquals(2, phoneList.size());
        }

        @Test
        @DisplayName("The getById method should return phone")
        void getByIdShouldReturnPhone() {
            MobilePhone phone = genSpecPhone("Samsung", "Galaxy A54", 512, 5000, 3);
            controller.create(phone);
            controller.getById(phone.getId());
            assertEquals(phone, controller.getById(phone.getId()));
        }

        @Test
        @DisplayName("The create method should create and put into memory new phone")
        void createShouldCreatePhone() {
            MobilePhone phone = genSpecPhone("Samsung", "Galaxy A54", 512, 5000, 3);
            controller.create(phone);
            List<MobilePhone> phoneList = controller.findAll();
            assertNotNull(phoneList, "List is empty");
            assertEquals(1, phoneList.size(), "The phone was not created");
            assertEquals(phone, phoneList.get(0), "The phone was not created");
        }

        @Test
        @DisplayName("The update method should update and put into memory updated phone")
        void updateShouldUpdateFilm() {
            genDefaultPhone(1);
            MobilePhone updatedPhone = genSpecPhone("Samsung", "Galaxy A54", 1024, 5000, 3);
            updatedPhone.setId(0L);
            controller.update(updatedPhone);
            List<MobilePhone> phoneList = controller.findAll();
            assertNotNull(phoneList, "List is empty");
            assertEquals(1, phoneList.size(), "The update should overwrite an already existing object");
            assertEquals(updatedPhone, phoneList.get(0), "The phone was not updated");
        }

        @Test
        @DisplayName("The remove method should remove phone from memory")
        void removeShouldRemovePhone() {
            genDefaultPhone(1);
            List<MobilePhone> phoneList = controller.findAll();
            assertEquals(1, phoneList.size());
            controller.remove(0L);
            phoneList = controller.findAll();
            assertEquals(0, phoneList.size());
        }
    }

    @Nested
    class WrongDataTests {
        @ParameterizedTest
        @ValueSource(ints = {
                0,
                -1
        })
        @DisplayName("Creating phone with non-positive memory size should fail")
        void createPhoneWithNonPositiveMemorySizeShouldFail(Integer memorySize) {
            MobilePhone phone = genSpecPhone("Samsung", "Galaxy A54", memorySize, 5000, 3);
            List<ConstraintViolation<MobilePhone>> violations = new ArrayList<>(validator.validate(phone));
            ExpectedViolation expectedViolation = new ExpectedViolation("memorySize",
                    "The phone's memory capacity should be positive number");
            assertEquals(expectedViolation.propertyPath, violations.get(0).getPropertyPath().toString());
            assertEquals(expectedViolation.message, violations.get(0).getMessage());
        }

        @ParameterizedTest
        @ValueSource(ints = {
                0,
                -1
        })
        @DisplayName("Creating phone with non-positive accumulator capacity should fail")
        void createPhoneWithNonPositiveAccumulatorCapacityShouldFail(Integer accumulatorCapacity) {
            MobilePhone phone = genSpecPhone("Samsung", "Galaxy A54", 128, accumulatorCapacity, 3);
            controller.create(phone);
            List<ConstraintViolation<MobilePhone>> violations = new ArrayList<>(validator.validate(phone));
            ExpectedViolation expectedViolation = new ExpectedViolation("accumulatorCapacity",
                    "The phone's accumulator capacity should be positive number");
            assertEquals(expectedViolation.propertyPath, violations.get(0).getPropertyPath().toString());
            assertEquals(expectedViolation.message, violations.get(0).getMessage());
        }

        @Test
        @DisplayName("Updating a non-existent phone should cause an exception")
        void updateNonExistentPhoneShouldCauseException() {
            MobilePhone phone = genSpecPhone("Samsung", "Galaxy A54", 128, 6000, 3);
            phone.setId(15L);
            assertThrows(NotFoundException.class, () -> controller.update(phone));
        }

        @Test
        @DisplayName("Removing a non-existent phone should cause an exception")
        void removeNonExistentPhoneShouldCauseException() {
            assertThrows(NotFoundException.class, () -> controller.remove(15L));
        }

        @Test
        @DisplayName("Getting a non-existent phone should cause an exception")
        void getNonExistentPhoneShouldCauseException() {
            assertThrows(NotFoundException.class, () -> controller.getById(15L));
        }
    }


}
