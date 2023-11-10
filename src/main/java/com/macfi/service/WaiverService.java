package com.macfi.service;

import com.macfi.model.Waiver;
import com.macfi.modelMapper.modelMapping;
import com.macfi.payload.WaiverDto;
import com.macfi.repository.WaiverRepository;
import org.springframework.stereotype.Service;

@Service
public class WaiverService {

    public WaiverRepository waiverRepository;

    public WaiverDto createWaiver(WaiverDto waiverDto) {
        return modelMapping.getInstance().mapToDto(waiverRepository.save(modelMapping.getInstance().mapToEntity(waiverDto, Waiver.class)), WaiverDto.class);
    }

    public WaiverDto getWaiverByStudentAndClassroom(Long id, Long idClassroom) {
        return modelMapping.getInstance().mapToDto(waiverRepository.findByStudentIdAndClassroomId(id, idClassroom), WaiverDto.class);
    }

    public WaiverDto getWaiverById(Long id) {
        return modelMapping.getInstance().mapToDto(waiverRepository.findById(id).isPresent(), WaiverDto.class);
    }


}
