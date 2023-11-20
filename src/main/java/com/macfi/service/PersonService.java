package com.macfi.service;

import com.macfi.exception.EntityNotFoundException;
import com.macfi.model.person.Person;
import com.macfi.modelMapper.modelMapping;
import com.macfi.payload.PersonDto;
import com.macfi.payload.RegisterCollegeIDDto;
import com.macfi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ProfessorService professorService;

    public PersonDto getPersonById(Long id) {
        Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Person not found"));

        PersonDto personDto = new PersonDto();
        personDto.setId(person.getId());
        personDto.setName(person.getName());
        personDto.setSocialName(person.getSocialName());
        personDto.setCpf(person.getCpf());
        personDto.setBirthDate(person.getBirthDate());
        personDto.setEmail(person.getEmail());
        personDto.setRegister(modelMapping.getInstance().mapToDto(person.getRegister(), RegisterCollegeIDDto.class));
        return personDto;

    }

    public Object getConcretePersonById(Long id) {
        try {
            return professorService.getProfessorById(id);
        } catch (EntityNotFoundException e) {
            return studentService.getStudentById(id);
        }
    }
}
