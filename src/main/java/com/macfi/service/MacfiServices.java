package com.macfi.service;


import com.macfi.model.Classroom;
import com.macfi.model.Notification;
import com.macfi.model.person.Student;
import com.macfi.model.utils.enums_class.StatusNotification;
import com.macfi.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MacfiServices {

    @Autowired
    public NotificationService notificationService;

    @Autowired
    public NotificationRepository notificationRepository;


    public void notifyAllStudents(Classroom classroom) {

        for (Student student: classroom.getStudents()) {
            Notification notification = new Notification();

            notification.setActive(true);
            notification.setRead(false);
            notification.setTitle("Nova chamada");
            notification.setStatusNotification(StatusNotification.urgent);
            notification.setSupportingText("Uma chamada da turma "+ classroom.getCourseName() + " foi criada");


            notification.setPerson(student);
            notificationRepository.save(notification);
        }

    }





}
