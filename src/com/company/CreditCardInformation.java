package com.company;

import java.util.Date;

public class CreditCardInformation {
    private String title;
    private String cardName;
    private int cardNumber;
    private int cardPIN;
    private int cardCVV;
    private Date exprDate;

    public CreditCardInformation(){
        super();
    }

    public CreditCardInformation(String title, String cardName, int cardNumber, int cardPIN, int cardCVV, Date exprDate) {
        this.title = title;
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.cardPIN = cardPIN;
        this.cardCVV = cardCVV;
        this.exprDate = exprDate;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCardPIN() {
        return cardPIN;
    }

    public void setCardPIN(int cardPIN) {
        this.cardPIN = cardPIN;
    }

    public int getCardCVV() {
        return cardCVV;
    }

    public void setCardCVV(int cardCVV) {
        this.cardCVV = cardCVV;
    }

    public Date getExprDate() {
        return exprDate;
    }

    public void setExprDate(Date exprDate) {
        this.exprDate = exprDate;
    }

    @Override
    public String toString() {
        return "CreditCardInformation{" +
                "title='" + title + '\'' +
                ", cardName='" + cardName + '\'' +
                ", cardNumber=" + cardNumber +
                ", cardPIN=" + cardPIN +
                ", cardCVV=" + cardCVV +
                ", exprDate=" + exprDate +
                '}';
    }
}
