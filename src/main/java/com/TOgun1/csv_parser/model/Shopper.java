package com.TOgun1.csv_parser.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "shoppers")
public class Shopper {

    @Id
    private Long userId; 
    private Integer age;
    private String gender;
    private String country;
    private String incomeLevel;
    private Double monthlySpend;
    private Double averageOrderValue;
    private String preferredPaymentMethod;
    private Boolean loyaltyProgramMember;

    public Shopper() {}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIncomeLevel() {
        return incomeLevel;
    }

    public void setIncomeLevel(String incomeLevel) {
        this.incomeLevel = incomeLevel;
    }

    public Double getMonthlySpend() {
        return monthlySpend;
    }

    public void setMonthlySpend(Double monthlySpend) {
        this.monthlySpend = monthlySpend;
    }

    public Double getAverageOrderValue() {
        return averageOrderValue;
    }

    public void setAverageOrderValue(Double averageOrderValue) {
        this.averageOrderValue = averageOrderValue;
    }

    public String getPreferredPaymentMethod() {
        return preferredPaymentMethod;
    }

    public void setPreferredPaymentMethod(String preferredPaymentMethod) {
        this.preferredPaymentMethod = preferredPaymentMethod;
    }

    public Boolean getLoyaltyProgramMember() {
        return loyaltyProgramMember;
    }

    public void setLoyaltyProgramMember(Boolean loyaltyProgramMember) {
        this.loyaltyProgramMember = loyaltyProgramMember;
    }


}