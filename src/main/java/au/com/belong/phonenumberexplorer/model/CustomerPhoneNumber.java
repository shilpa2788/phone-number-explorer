package au.com.belong.phonenumberexplorer.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class CustomerPhoneNumber {
    public CustomerPhoneNumber() {

    }

    private String custId;

    public Set<String> getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(Set<String> phoneNum) {
        this.phoneNum = phoneNum;
    }

    @NotNull
    @NotBlank(message = "Name is mandatory")
    private Set<String> phoneNum;
    private boolean isActivate;


    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }


    public boolean isActivate() {
        return isActivate;
    }

    public void setActivate(boolean activate) {
        isActivate = activate;
    }


    public CustomerPhoneNumber(String custId, Set<String> phoneNum, boolean isActivate) {


        this.custId = custId;
        this.phoneNum = phoneNum;
        this.isActivate = isActivate;
    }


    @Override
    public String toString() {
        return "CustomerPhoneNumber{" +

                ", custId='" + custId + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", isActivate='" + isActivate + '\'' +
                '}';
    }
}
