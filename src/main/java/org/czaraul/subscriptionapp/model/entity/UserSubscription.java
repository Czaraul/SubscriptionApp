package org.czaraul.subscriptionapp.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Date;

@Entity
@Builder
@Table(name = "user_subscriptions")
@AllArgsConstructor
public class UserSubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;


    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;


    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Column(name = "autorenew_enabled")
    private boolean autorenewEnabled;

    // Constructors, getters, and setters

    public UserSubscription() {
        // Default constructor needed by JPA
    }


    // ... other getters and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public boolean isAutorenewEnabled() {
        return autorenewEnabled;
    }

    public void setAutorenewEnabled(boolean autorenewEnabled) {
        this.autorenewEnabled = autorenewEnabled;
    }
}