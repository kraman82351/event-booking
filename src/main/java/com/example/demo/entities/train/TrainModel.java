package com.example.demo.entities.train;

import java.util.List;

import jakarta.persistence.Entity;

@Entity
public class TrainModel {
    private String trainId;
    private String trainNo;
    private List<List<Integer>> seats;
    private Map<String , Time>
}
