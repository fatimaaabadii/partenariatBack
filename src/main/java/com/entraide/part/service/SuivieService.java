package com.entraide.part.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entraide.part.entity.*;
import com.entraide.part.repository.*;
import com.entraide.part.service.*;

import java.math.BigDecimal;
import java.util.*;

@Service
public class SuivieService {
    
    @Autowired
    private SuivieRepository suivieRepository;

    @Autowired
    private PartenariatService partenariatService;

    public Suivie createSuivie(Suivie suivie){

        Partenariat existingPartenariat = partenariatService.getPartenariatById(suivie.getIdpart());
        suivie.setPartenariat(existingPartenariat);
        
        return suivieRepository.save(suivie);

    }

    public Suivie updateSuivie(Suivie newsuivie){

        Optional<Suivie> updatedSuivie = suivieRepository.findById(newsuivie.getId());
        
        Suivie sui = updatedSuivie.get();

        sui.setEtatAvancement(newsuivie.getEtatAvancement());
        sui.setEtat(newsuivie.getEtat());
        sui.setProjetOperationel(newsuivie.getProjetOperationel());
        sui.setCommentaire(newsuivie.getCommentaire());
        sui.setObjectifAtteints(newsuivie.getObjectifAtteints());
        sui.setDateSuivi(newsuivie.getDateSuivi());
        sui.setAvenant(newsuivie.getAvenant());

        Partenariat existingPartenariat = partenariatService.getPartenariatById(newsuivie.getIdpart());
        sui.setPartenariat(existingPartenariat);

        return suivieRepository.save(sui);
    }

    public Suivie getSuivieById(Long suivieId) {
        return suivieRepository.findById(suivieId)
                                 .orElseThrow(() -> new NoSuchElementException("suivie not found"));
    }

    public List<Suivie> getSuiviebyetatavancement(BigDecimal etatav){

        List<Suivie> suivies = suivieRepository.findByetatAvancement(etatav);
        return suivies;
    }

    public List<Suivie> getSuiviebyPartenariat(Partenariat partenariat) {
        return suivieRepository.findByPartenariat(partenariat);
    }

    public List<Suivie> getSuiviebyetat(String etat){

        List<Suivie> suivies = suivieRepository.findByetat(etat);
        return suivies;
    }

    public List<Suivie> getSuiviebyoprerationel(String operat){

        List<Suivie> suivies = suivieRepository.findByprojetOperationel(operat);
        return suivies;
    }

    public List<Suivie> getAllSuivies() {
        return suivieRepository.findAll();
    }
    
    public void deleteSuivieByPartenariatId(Long partenariatId) {
        suivieRepository.deleteSuivieByPartenariatId(partenariatId);
    }
    
    public void deleteSuiviById(Long suiviId) {
        Optional<Suivie> sui = suivieRepository.findById(suiviId);
        if (sui != null) {
            suivieRepository.deleteById(suiviId);
        } else {
            throw new IllegalArgumentException("Suivi not found");
        }
    }

}