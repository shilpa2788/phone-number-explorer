package au.com.belong.phonenumberexplorer.controller;


import au.com.belong.phonenumberexplorer.model.CustomerPhoneNumber;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import static au.com.belong.phonenumberexplorer.model.ResponseHandler.generateResponse;


@CrossOrigin
@RestController
@Slf4j
@Data
@RequestMapping(value = "/api/")
public class PhoneNumberController {

    Set<CustomerPhoneNumber> customerPhoneNumbers = new HashSet<CustomerPhoneNumber>();

    {
        HashMap<String, Boolean> phoneNumStatusMap = new HashMap<>();
        phoneNumStatusMap.put("0478929142", false);
        phoneNumStatusMap.put("0478929152", false);
        phoneNumStatusMap.put("0478929162", false);


        HashMap<String, Boolean> phoneNumStatusMap1 = new HashMap<>();
        phoneNumStatusMap1.put("0478929242", false);
        phoneNumStatusMap1.put("0478929252", true);
        phoneNumStatusMap1.put("0478929262", false);

        customerPhoneNumbers.add(new CustomerPhoneNumber("cust1", phoneNumStatusMap));
        customerPhoneNumbers.add(new CustomerPhoneNumber("cust2", phoneNumStatusMap1));

    }

    /**
     * While fetching from DB need to consider the following
     * 1. Pagination using database limit , offset combo
     */
    @Operation(summary = "This method is to fetch all phone numbers")
    @GetMapping(value = "/getAllPhoneNumbers",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<String> getAllPhoneNumbers() {
        final long startTime = System.currentTimeMillis();
        log.info("API call requested for getAllPhoneNumbers : _" + startTime);
        Set<String> allPhoneNum = new HashSet<>();
        customerPhoneNumbers.stream().map(element -> element.getPhoneNumStatusMap()
                .keySet()).forEach(allPhoneNum::addAll);
        final long endTime = System.currentTimeMillis();
        log.info("Time taken to provide response for API getAllPhoneNumbers :_"
                + startTime + " is : " + (endTime - startTime) + " ms");
        return allPhoneNum;
    }


    @Operation(summary = "This method is to fetch all phone numbers for a given customer.")
    @GetMapping(value = "/getPhoneNumbers/{custId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<String> getPhoneNumberByCustId(@PathVariable(value = "custId") String custId) {
        final long startTime = System.currentTimeMillis();
        log.info("API call requested for API getPhoneNumbers for custId :  " + custId + "_" + startTime);
        for ( CustomerPhoneNumber customerPhoneNumber : customerPhoneNumbers ) {
            if (customerPhoneNumber.getCustId().equalsIgnoreCase(custId)) {
                return Optional.of(customerPhoneNumber).map(CustomerPhoneNumber::getPhoneNum).orElse(null);
            }
        }
        final long endTime = System.currentTimeMillis();
        log.info("Time taken to provide response for API getPhoneNumbers for custId : "
                + custId + "_" + startTime + " is : " + (endTime - startTime) + " ms");
        return Optional.<CustomerPhoneNumber>empty().map(CustomerPhoneNumber::getPhoneNum).orElse(null);
    }

    @Operation(summary = "This Post method is to activate the given phone number of a customer. " +
            "Expects phone number as input")
    @PostMapping(value = "/activatePhoneNumber",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public AtomicReference<ResponseEntity<Object>> activatePhoneNumber(@RequestBody final Set<String> phoneNumber) {
        final long startTime = System.currentTimeMillis();
        log.info("API call requested for API activatePhoneNumber :  " + phoneNumber + "_" + startTime);
        AtomicReference<ResponseEntity<Object>> responseObj = new AtomicReference<>();
        customerPhoneNumbers.forEach(customer -> {
            customer.getPhoneNumStatusMap().forEach((number, status) -> {
                phoneNumber.stream().filter(num -> num.equalsIgnoreCase(number)).forEach(num -> {
                    responseObj.set(status ? generateResponse("Number activated already!", HttpStatus.OK)
                            : generateResponse("Phone number activated !", HttpStatus.OK));
                });
            });
        });
        final long endTime = System.currentTimeMillis();
        log.info("Time taken to provide response for API activatePhoneNumber for number: "
                + phoneNumber + "_" + startTime + " is : " + (endTime - startTime) + " ms");
        return responseObj;

    }
}

