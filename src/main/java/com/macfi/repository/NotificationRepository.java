package com.macfi.repository;

import com.macfi.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {


    @Query(value = "select n from Notification n where n.person.id = :personId and n.isActive = true",
            countQuery = "select count(n) from Notification n where n.person.id = :personId and n.isActive = true")
    List<Notification> findByPersonIdAndIsActive(Long personId);


}
