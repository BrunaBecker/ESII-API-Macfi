package com.macfi.repository;

import com.macfi.model.Waiver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WaiverRepository extends JpaRepository<Waiver, Long> {

    @Query("select w from Waiver w left join fetch w.attendanceStatus ats left join fetch ats.attendance a left join fetch a.classroom c where w.student.id = :id and c.id = :idClassroom")
    Waiver findByStudentIdAndClassroomId(Long id, Long idClassroom);


}
