package com.entraide.part.controller;

import org.springframework.web.bind.annotation.RestController;

import com.entraide.part.entity.*;
import com.entraide.part.service.*;

import jakarta.transaction.Transactional;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;


@RestController
@CrossOrigin("*")
@RequestMapping(value = "/suivie", method=RequestMethod.GET)
public class SuivieController {
    
    @Autowired
    private SuivieService suivieService;

    @Autowired
    private PartenariatService partenariatService;

    
    
    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
    @PutMapping("/create/{idpart}")
    public ResponseEntity<Suivie> createSuivie(@PathVariable Long idpart, @RequestBody Suivie suivie) {
        Suivie createdSuivie = suivieService.createSuivie(suivie);
        return new ResponseEntity<>(createdSuivie, HttpStatus.CREATED);
    }
    
    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
    @PutMapping("/update/{idpart}/{id}")
    public ResponseEntity<Suivie> updateSuivie(@PathVariable Long idpart, @PathVariable Long id, @RequestBody Suivie suivie) {
        Suivie updatedSuivie = suivieService.updateSuivie(suivie);
        return new ResponseEntity<>(updatedSuivie, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
    @PostMapping("/suiviebyid")
    public ResponseEntity<Suivie> getSuivieById(@RequestBody Long suivieId) {
        Suivie suivie = suivieService.getSuivieById(suivieId);
        return new ResponseEntity<>(suivie, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
    @PostMapping("/byetatavancement")
    public ResponseEntity<List<Suivie>> getSuivieByEtatAvancement(@RequestBody BigDecimal etatav) {
        List<Suivie> suivies = suivieService.getSuiviebyetatavancement(etatav);
        return new ResponseEntity<>(suivies, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
    @PostMapping("/byetat")
    public ResponseEntity<List<Suivie>> getSuivieByEtat(@RequestBody String etat) {
        List<Suivie> suivies = suivieService.getSuiviebyetat(etat);
        return new ResponseEntity<>(suivies, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
    @PostMapping("/byoperationel")
    public ResponseEntity<List<Suivie>> getSuivieByOperationel(@RequestBody String operat) {
        List<Suivie> suivies = suivieService.getSuiviebyoprerationel(operat);
        return new ResponseEntity<>(suivies, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
    @PostMapping("/byPartenariatid/{partenariatId}")
    public ResponseEntity<List<Suivie>> getSuivieByPartenariat(@PathVariable Long partenariatId) {
        Partenariat partenariat = partenariatService.getPartenariatById(partenariatId);
        List<Suivie> suivies = suivieService.getSuiviebyPartenariat(partenariat);
        return ResponseEntity.ok(suivies);
    }

    @GetMapping("/allsuivies")
    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
    public List<Suivie> getAllSuivies() {
        return suivieService.getAllSuivies();
    }
    
    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
    @Transactional
    @DeleteMapping("/partenariat/{partenariatId}")
    public void deleteSuivieByPartenariatId(@PathVariable Long partenariatId) {
        suivieService.deleteSuivieByPartenariatId(partenariatId);
    }
    
    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
    @DeleteMapping("/{idpart}/{suiviId}")
    public ResponseEntity<String> deleteSuiviById(@PathVariable Long idpart, @PathVariable Long suiviId) {
        suivieService.deleteSuiviById(suiviId);
        return new ResponseEntity<>("Suivi deleted successfully", HttpStatus.OK);
    }
}