package com.macfi.repository;

import com.macfi.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByDateBetween(Date start, Date end);

    Event findByDate(Date date);

}
