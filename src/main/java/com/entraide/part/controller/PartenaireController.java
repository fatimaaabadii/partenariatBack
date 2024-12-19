package com.entraide.part.controller;


import org.springframework.web.bind.annotation.RestController;

import com.entraide.part.entity.Partenaire;
import com.entraide.part.service.PartenaireService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;


@RestController
@CrossOrigin("*")
@RequestMapping("/partenaire")

public class PartenaireController {
    
    @Autowired
    private PartenaireService partenaireService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
    public ResponseEntity<Partenaire> createPartenaire(@RequestBody Partenaire partenaire) {
        Partenaire createdPartenaire = partenaireService.createpartenaire(partenaire);
        return new ResponseEntity<>(createdPartenaire, HttpStatus.CREATED);
    }
    @GetMapping("/getPartenaires")
    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
	public ResponseEntity<List<Partenaire>> findAllPartenaires(){
    	 return new ResponseEntity<>(partenaireService.getAllPartenaire(), HttpStatus.OK);
	    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
    public ResponseEntity<Partenaire> updatePartenaire(@PathVariable Long id, @RequestBody Partenaire partenaire) {
        Partenaire updatedPartenaire = partenaireService.updatepartenaire(partenaire);
        return new ResponseEntity<>(updatedPartenaire, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
    public ResponseEntity<Partenaire> getPartenaireById(@PathVariable Long id) {
        Partenaire partenaire = partenaireService.getpartenaireById(id);
        return new ResponseEntity<>(partenaire, HttpStatus.OK);
    }

    @PostMapping("/byname")
    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
    public ResponseEntity<List<Partenaire>> getPartenaireByName(@RequestBody String partenaireName) {
        System.out.println("test");
        List<Partenaire> partenaires = partenaireService.getPartenairebyname(partenaireName);
        return new ResponseEntity<>(partenaires, HttpStatus.OK);
    }

    @GetMapping("/allpart")
    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
    public List<Partenaire> getAllPartenaires() {
        return partenaireService.getAllPartenaires();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
    public ResponseEntity<String> deletePartenaireById(@PathVariable Long id) {
        partenaireService.deletePartenaireById(id);
        return new ResponseEntity<>("Partenaire deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/partenaires")
    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
    public Page<Partenaire> getPartenaires(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        return partenaireService.getPartenaires(PageRequest.of(page, size));
    }


}