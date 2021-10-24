package au.com.belong.phonenumberexplorer.controller;


import au.com.belong.phonenumberexplorer.exceptions.InvalidInputException;
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
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import static au.com.belong.phonenumberexplorer.model.ResponseHandler.generateResponse;


@CrossOrigin
@RestController
@Slf4j
@Data
@RequestMapping(value = "/api/v1")
public class PhoneNumberController {
    /**
     * Static data which gets initialized
     */

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
        Set<String> phoneNumberResponse = new HashSet<>();
        log.info("API call requested for API getPhoneNumbers for custId :  " + custId + ": startTime is -" + startTime);

        customerPhoneNumbers.stream().filter(customerPhoneNumber -> customerPhoneNumber.getCustId()
                .equalsIgnoreCase(custId)).map(customerPhoneNumber -> customerPhoneNumber.getPhoneNum(customerPhoneNumber))
                .forEach(phoneNumberResponse::addAll);

        // if no phone number return the proper error message
        if (phoneNumberResponse.isEmpty()) {
            throw new InvalidInputException(custId + " is not valid");
        }
        final long endTime = System.currentTimeMillis();
        log.info("Time taken to provide response for API getPhoneNumbers for custId : "
                + custId + " :_" + startTime + " is : " + (endTime - startTime) + " ms");
        return phoneNumberResponse;

    }

    @Operation(summary = "This Post method is to activate the given phone number of a customer. " +
            "Expects phone number as input")
    @PostMapping(value = "/activatePhoneNumber",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public AtomicReference<ResponseEntity<Object>> activatePhoneNumber(@RequestBody final Set<String> phoneNumber) {

        final long startTime = System.currentTimeMillis();
        AtomicReference<ResponseEntity<Object>> responseObj = new AtomicReference<>();

        log.info("API call requested for API activatePhoneNumber :  " + phoneNumber + "_" + startTime);

        customerPhoneNumbers.forEach(customer -> {
            customer.getPhoneNumStatusMap().forEach((number, status) -> {
                phoneNumber.stream().filter(num -> num.equalsIgnoreCase(number)).map(num -> status
                        ? generateResponse("Phone number activated already!", HttpStatus.OK)
                        : generateResponse("Phone number activated !", HttpStatus.OK)).forEach(responseObj::set);
            });
        });
        // if no response Object set that means Num is not present in DB
        if (responseObj.toString().equalsIgnoreCase("null")) {
            responseObj.set(generateResponse("Phone number NotFound !", HttpStatus.NOT_FOUND));
        }

        final long endTime = System.currentTimeMillis();
        log.info("Time taken to provide response for API activatePhoneNumber for number: "
                + phoneNumber + "_" + startTime + " is : " + (endTime - startTime) + " ms");
        return responseObj;

    }
}

