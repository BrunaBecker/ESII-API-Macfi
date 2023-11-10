package com.macfi.controllertests;


import com.macfi.controller.*;
import com.macfi.repository.*;
import com.macfi.service.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AttendanceController.class)
public class AttendanceControllerTest {

    @Test
    public void testCreateAttendance() throws Exception {


    }


}