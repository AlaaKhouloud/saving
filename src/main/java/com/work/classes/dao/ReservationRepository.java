package com.work.classes.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.work.classes.entities.Reservation;
import com.work.classes.entities.ReservationPK;
import com.work.classes.entities.Terrain;

public interface ReservationRepository extends JpaRepository<Reservation, ReservationPK>{


}
