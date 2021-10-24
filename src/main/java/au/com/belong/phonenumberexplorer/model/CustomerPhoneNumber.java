package au.com.belong.phonenumberexplorer.model;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class CustomerPhoneNumber {
    public CustomerPhoneNumber() {
    }

    public CustomerPhoneNumber(String custId, HashMap<String, Boolean> phoneNumStatusMap) {
        this.custId = custId;
        this.phoneNumStatusMap = phoneNumStatusMap;
    }

    @NotBlank
    private String custId;
    //Each customer can have N numbers with different activation status
    private  HashMap<String, Boolean> phoneNumStatusMap;


    public String getCustId() {
        return custId;
    }
    public void setCustId(String custId) {
        this.custId = custId;
    }


    public HashMap<String, Boolean> getPhoneNumStatusMap() {
        return phoneNumStatusMap;
    }

    public void setPhoneNumStatusMap(HashMap<String, Boolean> phoneNumStatusMap) {
        this.phoneNumStatusMap = phoneNumStatusMap;
    }


    @Override
    public String toString() {
        return "CustomerPhoneNumber{" +
                ", custId='" + custId + '\'' +
                ", phoneNumStatusMap='" + phoneNumStatusMap + '\'' +
                '}';
    }

    /**
     *
     * @param customerPhoneNumber
     * @return phone numbers set of an associated customer
     */
    public static Set<String> getPhoneNum(CustomerPhoneNumber customerPhoneNumber) {
        Set<String> phoneNum = new HashSet<>();
        customerPhoneNumber.getPhoneNumStatusMap().forEach((key, value) -> phoneNum.add(key));
        return phoneNum;
    }
}
