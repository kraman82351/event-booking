package com.harshchauhan.irctc_core.entities.ticket;

import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketModel {

    @Id
    @UuidGenerator
    @Column(name = "ticketId", updatable = false, nullable = false)
    private UUID ticketId;

    @NotNull
    @Column(name = "userId", updatable = false, nullable = false)
    private UUID userId;

    @NotNull
    @Column(name = "sourceStation", nullable = false)
    private String sourceStation;

    @NotNull
    @Column(name = "destinationStation", nullable = false)
    private String destinationStation;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date journeyStartDate;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date journeyEndDate;
}
