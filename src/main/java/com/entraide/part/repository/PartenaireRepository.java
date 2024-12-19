package com.entraide.part.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entraide.part.entity.Partenaire;

import java.util.*;
public interface PartenaireRepository extends JpaRepository<Partenaire, Long>{
     List<Partenaire> findByPartenaire(String partenaire);
     List<Partenaire> findAll();}