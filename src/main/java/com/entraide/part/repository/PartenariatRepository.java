package com.entraide.part.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entraide.part.entity.*;

public interface PartenariatRepository extends JpaRepository<Partenariat, Long>{
    List<Partenariat> findByDomaine(String domaine);
    List<Partenariat> findByType(String type);


    List<Partenariat> findByPartenairesId(Long partenaireId);
	
}