package com.ThankSens.Hentori.Dto;

public class ReportDto {
    private double total;
    private int amount;
    private double shirtTotal;
    private int shirtAmount;
    private double suitTotal;
    private int suitAmount;
    private double trousersTotal;
    private int trousersAmount;
    private double accessoryTotal;
    private int accessoryAmount;
    private double target;

    public ReportDto() {
    }

    public ReportDto(double total, int amount, double shirtTotal, int shirtAmount, double suitTotal, int suitAmount, double trousersTotal, int trousersAmount, double accessoryTotal, int accessoryAmount, double target) {
        this.total = total;
        this.amount = amount;
        this.shirtTotal = shirtTotal;
        this.shirtAmount = shirtAmount;
        this.suitTotal = suitTotal;
        this.suitAmount = suitAmount;
        this.trousersTotal = trousersTotal;
        this.trousersAmount = trousersAmount;
        this.accessoryTotal = accessoryTotal;
        this.accessoryAmount = accessoryAmount;
        this.target = target;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getShirtTotal() {
        return shirtTotal;
    }

    public void setShirtTotal(double shirtTotal) {
        this.shirtTotal = shirtTotal;
    }

    public int getShirtAmount() {
        return shirtAmount;
    }

    public void setShirtAmount(int shirtAmount) {
        this.shirtAmount = shirtAmount;
    }

    public double getSuitTotal() {
        return suitTotal;
    }

    public void setSuitTotal(double suitTotal) {
        this.suitTotal = suitTotal;
    }

    public int getSuitAmount() {
        return suitAmount;
    }

    public void setSuitAmount(int suitAmount) {
        this.suitAmount = suitAmount;
    }

    public double getTrousersTotal() {
        return trousersTotal;
    }

    public void setTrousersTotal(double trousersTotal) {
        this.trousersTotal = trousersTotal;
    }

    public int getTrousersAmount() {
        return trousersAmount;
    }

    public void setTrousersAmount(int trousersAmount) {
        this.trousersAmount = trousersAmount;
    }

    public double getAccessoryTotal() {
        return accessoryTotal;
    }

    public void setAccessoryTotal(double accessoryTotal) {
        this.accessoryTotal = accessoryTotal;
    }

    public int getAccessoryAmount() {
        return accessoryAmount;
    }

    public void setAccessoryAmount(int accessoryAmount) {
        this.accessoryAmount = accessoryAmount;
    }

    public double getTarget() {
        return target;
    }

    public void setTarget(double target) {
        this.target = target;
    }
}
