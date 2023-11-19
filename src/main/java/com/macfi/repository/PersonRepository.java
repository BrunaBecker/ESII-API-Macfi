package com.macfi.repository;

import com.macfi.model.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("select p from Person p where p.register.identifier = :identifier")
    Person findByIdentifier(String identifier);

    @Query("select p from Person p where p.email = :email")
    Person findByEmail(String email);

    @Query("select p from Person p where p.register.identifier = :identifier and p.password = :password")
    Person findByIdentifierAndPassword(String identifier, String password);


}
