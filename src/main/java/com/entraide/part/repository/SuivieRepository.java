package com.entraide.part.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entraide.part.entity.*;

import java.math.BigDecimal;
import java.util.*;


public interface SuivieRepository extends JpaRepository<Suivie, Long>{

    List<Suivie> findByetatAvancement(BigDecimal etatAvancement);
    List<Suivie> findByetat(String etat);
    List<Suivie> findByprojetOperationel(String projetOperationel);

    List<Suivie> findByPartenariat(Partenariat partenariat);
    void deleteSuivieByPartenariatId(Long partenariatId);
}