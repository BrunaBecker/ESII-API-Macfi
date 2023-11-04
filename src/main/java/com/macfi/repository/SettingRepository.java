package com.macfi.repository;

import com.macfi.model.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SettingRepository extends JpaRepository<Setting, Long> {

    @Query("select s from Setting s where s.person.register.identifier = :identify")
    Setting findByPersonIdentifier(Long identify);
}
