package org.czaraul.subscriptionapp.controller.model;

import lombok.Builder;

import java.util.Date;

@Builder
public class UserSubscriptionRequest {

    private Long id;
    private String userName;
    private String productName;
    private Date startDate;
    private Date endDate;

    public UserSubscriptionRequest() { // Default constructor
    }

    public UserSubscriptionRequest(Long id, String userName, String productName, Date startDate, Date endDate) {
        this.id = id;
        this.userName = userName;
        this.productName = productName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public UserSubscriptionRequest(String userName, String productName, Date startDate, Date endDate) {
        this.userName = userName;
        this.productName = productName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}
