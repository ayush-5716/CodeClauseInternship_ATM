package com.codeclause.atm.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "kilo")
public class user_ent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    public user_ent() {
    };

    String first_name;
    String last_name;
    long acc_number;
    long amount;
    long pin;

    public long getPin() {
        return pin;
    }

    public void setPin(long pin) {
        this.pin = pin;
    }

    public long getAmount() {
        return amount;
    }

    public user_ent(String first_name, String last_name, long acc_number, long amount) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.acc_number = acc_number;
        this.amount = amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getId() {
        return user_id;
    }

    public void setId(long id) {
        this.user_id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public long getAcc_number() {
        return acc_number;
    }

    public void setAcc_number(long acc_number) {
        this.acc_number = acc_number;
    }
}