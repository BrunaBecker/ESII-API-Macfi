package com.macfi.service;

import com.macfi.model.Waiver;
import com.macfi.repository.WaiverRepository;
import org.springframework.stereotype.Service;

@Service
public class WaiverService {

    public WaiverRepository waiverRepository;

    public Waiver createWaiver(Waiver waiver)  {
        return waiverRepository.save(waiver);
    }

    public Waiver getWaiverByStudentAndClassroom(Long id, Long idClassroom) {
        return waiverRepository.findByStudentAndClassroom(id, idClassroom);
    }


}
