package com.sawah.signalrtest.common.misc;

public class CardSize {


    float cardWidth = 0;
    float cardHeight = 0;
    float spaceOffset = 0;
    float screenWidth = 0;
    float screenHeight = 0;
    private float density;


    public CardSize(float cardWidth, float cardHeight, float spaceOffset, float screenWidth, float screenHeight, float density) {
        this.cardWidth = cardWidth;
        this.cardHeight = cardHeight;
        this.spaceOffset = spaceOffset;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.density = density;
    }

    public float getCardWidth() {
        return cardWidth;
    }

    public void setCardWidth(float cardWidth) {
        this.cardWidth = cardWidth;
    }

    public float getCardHeight() {
        return cardHeight;
    }

    public void setCardHeight(float cardHeight) {
        this.cardHeight = cardHeight;
    }

    public float getSpaceOffset() {
        return spaceOffset;
    }

    public void setSpaceOffset(float spaceOffset) {
        this.spaceOffset = spaceOffset;
    }

    public float getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(float screenWidth) {
        this.screenWidth = screenWidth;
    }

    public float getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(float screenHeight) {
        this.screenHeight = screenHeight;
    }

    public float getDensity() {
        return density;
    }

    public void setDensity(float density) {
        this.density = density;
    }
}
