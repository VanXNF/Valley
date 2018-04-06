package com.vanxnf.photovalley.features.About.Entity;

public class AboutCardItem {

    private Integer cardTitleId;

    private int cardSize;

    private int startPosition;

    public AboutCardItem(Integer cardTitleId, int cardSize, int startPosition) {
        this.cardTitleId = cardTitleId;
        this.cardSize = cardSize;
        this.startPosition = startPosition;
    }

    public Integer getCardTitleId() {
        return cardTitleId;
    }

    public void setCardTitleId(Integer cardTitleId) {
        this.cardTitleId = cardTitleId;
    }

    public int getCardSize() {
        return cardSize;
    }

    public void setCardSize(int cardSize) {
        this.cardSize = cardSize;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }
}
