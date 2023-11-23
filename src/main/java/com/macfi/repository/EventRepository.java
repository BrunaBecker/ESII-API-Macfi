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


    @Query("select e from Event e where e.classroom.id = ?1")
    List<Event> findByClassroomId(Long classroomId);


    @Query("select distinct e from Event e join Classroom c on e.classroom.id = c.id join c.students sc where sc.id = ?1 and e.date between ?2 and ?3")
    List<Event> findByDateBetweenAndStudentId(Long personId, Date start, Date end);

    @Query("select distinct e from Event e join Classroom c on  e.classroom.id = c.id join c.professor pf where pf.id = ?1 and e.date between ?2 and ?3")
    List<Event> findByDateBetweenAndProfessorId(Long personId, Date start, Date end);

}
