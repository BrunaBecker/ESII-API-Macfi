package com.macfi.repository;

import com.macfi.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByDateBetween(Date start, Date end);

    @Query("select e from Event e where e.classroom.id = ?1 and e.date between ?2 and ?3")
    List<Event> findByClassroomIdAndDateBetween(Long id, Date dateStart, Date dateEnd);


}
