package com.nju.edu.cn.model;

/**
 * Created by shea on 2018/10/21.
 */
public class CustomerBasic {
    private String customerType;
    private String customerSegment;
    private String partnerCustomerSegment;
    private CustomerParticular customerParticulars;

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getCustomerSegment() {
        return customerSegment;
    }

    public void setCustomerSegment(String customerSegment) {
        this.customerSegment = customerSegment;
    }

    public String getPartnerCustomerSegment() {
        return partnerCustomerSegment;
    }

    public void setPartnerCustomerSegment(String partnerCustomerSegment) {
        this.partnerCustomerSegment = partnerCustomerSegment;
    }

    public CustomerParticular getCustomerParticulars() {
        return customerParticulars;
    }

    public void setCustomerParticulars(CustomerParticular customerParticulars) {
        this.customerParticulars = customerParticulars;
    }
}
