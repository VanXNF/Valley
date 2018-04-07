package com.vanxnf.photovalley.features.UserProfile.Entity;


/**
 * Created by VanXN on 2018/4/6.
 */

public class SubscribeItem {

    private String description;
    private String amount;

    public SubscribeItem(String description, String amount) {
        this.amount = amount;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
