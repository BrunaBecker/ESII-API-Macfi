package com.macfi.repository;


import com.macfi.model.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {

//    @Query("select c from Calendar c where c.date = :date")
//    Calendar findByDate(Date date);

}
