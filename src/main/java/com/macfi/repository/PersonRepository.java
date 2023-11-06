package com.macfi.repository;

import com.macfi.model.person.Person;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PersonRepository<T extends Person, L extends Number> extends JpaRepository<Person, Long> {

    @Query("select p from Person p where p.register.identifier = :identifier")
    T findByIdentifier(String identifier);

    @Query("select p from Person p where p.email = :email")
    T findByEmail(String email);

    @Query("select p from Person p where p.register.identifier = :identifier and p.password = :password")
    T findByIdentifierAndPassword(String identifier, String password);

    @Query("select p from Person p ")
    List<T> findAllByRepository(Sort sort);


}
