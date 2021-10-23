package au.com.belong.phonenumberexplorer.controller;


import au.com.belong.phonenumberexplorer.model.CustomerPhoneNumber;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;


@CrossOrigin
@RestController
@Slf4j
@Data
@RequestMapping(value = "/api/")
public class PhoneNumberController {

    Set<CustomerPhoneNumber> customerPhoneNumbers = new HashSet<CustomerPhoneNumber>();

    {
        Set<String> phoneNumCust1 = new HashSet<>();

        phoneNumCust1.add("112121212121");
        phoneNumCust1.add("112121212121");
        phoneNumCust1.add("112121212121");
        phoneNumCust1.add("112121212121");


        HashSet<String> phoneNumCust2 = new HashSet<>();
        customerPhoneNumbers.add(new CustomerPhoneNumber("asasas", phoneNumCust1, false));
        customerPhoneNumbers.add(new CustomerPhoneNumber("asasas1", phoneNumCust2, true));
    }

    @GetMapping(value = "/getAllPhoneNumbers",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<CustomerPhoneNumber> getAllPhoneNumbers() {
        return customerPhoneNumbers;
    }


    @GetMapping(value = "/getPhoneNumbers/{custId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<String> getPhoneNumberByCustId(@PathVariable(value = "custId") String custId) {

        return customerPhoneNumbers.stream()
                .filter(customerPhoneNumber -> customerPhoneNumber.getCustId().equalsIgnoreCase(custId))
                .findFirst().map(CustomerPhoneNumber::getPhoneNum).orElse(null);


    }

    @PostMapping(value = "/activatePhoneNumber",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Apis for UI enablement, Fetch paged elements for the search_string")
    public ResponseEntity activatePhoneNumber(String phoneNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(" ");

    }
}

