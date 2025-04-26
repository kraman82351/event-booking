package com.harshchauhan.irctc_core.entities.train;

import java.util.List;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @NotBlank
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainSeatModel> seats;

    // @Column(name = "sourceStation", nullable = false)
    // private List<Integer> seats;

    // private Map<String, Date> stationVsArrivalTime;

    // private List<String> stations;
}
