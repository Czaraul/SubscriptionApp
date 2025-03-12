package org.czaraul.subscriptionapp.controller.model;

import lombok.Builder;

import java.util.Date;

@Builder
public class UserSubscriptionResponse {

    private Long id;

    private Date startDate;

    private Date endDate;

    public UserSubscriptionResponse() {
    }

    public UserSubscriptionResponse(Long id, Date startDate, Date endDate) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
