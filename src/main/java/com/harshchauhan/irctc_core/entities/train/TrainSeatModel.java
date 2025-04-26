package com.harshchauhan.irctc_core.entities.train;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "train_seats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainSeatModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "train_id", nullable = false)
    private TrainModel train;

    @NotNull
    @Column(nullable = false)
    private int seatNumber;

    @Column(nullable = false)
    private boolean isBooked;
}
