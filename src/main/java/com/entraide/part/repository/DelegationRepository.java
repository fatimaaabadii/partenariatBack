package com.entraide.part.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entraide.part.entity.*;

import java.util.*;
public interface DelegationRepository extends JpaRepository<Delegation, Long>{
    List<Delegation> findBydelegation(String delegation);
}