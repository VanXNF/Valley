package com.vanxnf.photovalley.features.UserProfile.Entity;


/**
 * Created by VanXN on 2018/4/6.
 */

public class SubscribeItem {

    private Integer description;
    private Integer amount;

    public SubscribeItem(Integer description, Integer amount) {
        this.description = description;
        this.amount = amount;
    }

    public Integer getDescription() {
        return description;
    }

    public void setDescription(Integer description) {
        this.description = description;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
