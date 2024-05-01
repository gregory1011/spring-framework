package com.cydeo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class MovieCinema extends BaseModel{

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDate dateTime;

    public MovieCinema(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cinema cinema;

}
