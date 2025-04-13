package com.example.demo.entities.train;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "trains")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainModel {
    @Id
    @UuidGenerator
    @Column(name = "trainId", updatable = false, nullable = false)
    private String trainId;

    // @Column(name = "sourceStation", nullable = false)
    // private List<Integer> seats;

    // private Map<String, Date> stationVsArrivalTime;

    // private List<String> stations;
}
