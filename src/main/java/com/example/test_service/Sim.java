package com.example.test_service;

public class Sim {

    private String name;
    private String companyName;
    private String address;

    Sim(String name , String companyName , String address){
        this.name = name;
        this.companyName = companyName;
        this.address = address;
    }

    public void createSim(){
        System.out.println("New sim create with company name : " + name);
    }

}
