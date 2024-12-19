package com.entraide.part.controller;

import org.springframework.web.bind.annotation.RestController;

import com.entraide.part.entity.*;
import com.entraide.part.service.PartenaireService;
import com.entraide.part.service.PartenariatService;
import com.entraide.part.service.SuivieService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/part", method=RequestMethod.GET)
public class PartenariatController {
    
	 @Autowired
	    PartenariatService partenariatService;

	    @Autowired
	    PartenaireService partenaireService;

	    @Autowired
	    SuivieService suivieService;

	    @PostMapping("/create")
	    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
	    public ResponseEntity<Partenariat> createPartenariat(@RequestBody Partenariat partenariat
	                                                         /*@RequestBody List<Long> idDeleg,
	                                                         @RequestBody List<Long> idpartenaire,
	                                                         @RequestBody List<Long> idcommune*/) {


	        System.out.println(partenariat.getIdPartenaires());
	        
	        List<Long> idcommunes = partenariat.getIdCommunes();
	        System.out.println(idcommunes);
	        
	        Set<Partenaire> listePartenaires = partenariat.getPartenaires();
	        //List<Integer> liste = new ArrayList<>(listePartenaires);

	        for (Partenaire partenaire : listePartenaires) {
	            Partenaire createpartenaire = partenaireService.createpartenaire(partenaire);
	        }
	        //List<Long> idpartenaires = partenariat.getIdPartenaires();
	        //System.out.println(idpartenaires);
	        List<Long> idDelegs = partenariat.getIdDelegations();
	        System.out.println(idDelegs);
	       

	        Partenariat createdPartenariat = partenariatService.createPartenariat(partenariat, idDelegs, /*idpartenaires,*/idcommunes);
	        return ResponseEntity.ok().body(createdPartenariat);
	    }


	    
	    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
	    @Transactional
	    @PutMapping("/update/{id}")
	    public ResponseEntity<Partenariat> updatePartenariat(@PathVariable Long id,
	                                                         @RequestBody Partenariat partenariat
	                                                         /*@RequestParam List<Long> idDeleg,
	                                                         @RequestParam List<Long> idpartenaire,
	                                                         @RequestParam List<Long> idcommune*/) {

	        List<Long> idcommune = partenariat.getIdCommunes();
	        //List<Long> idpartenaire = partenariat.getIdPartenaires();
	        List<Long> idDeleg = partenariat.getIdDelegations();
	       

	        Set<Partenaire> listePartenaires = partenariat.getPartenaires();
	        System.out.println(listePartenaires);

	        for (Partenaire partenaire : listePartenaires) {
	            //Partenaire updatepartenaire = partenaireService.updatepartenaire(partenaire);

	            System.out.println(partenaire);
	            if (partenaire.getId() == null) {

	                //Long newId = partenaireService.getLastAutoIncrementValue();
	                //partenaire.setId(newId);
	                //System.out.println(partenaire);
	                Partenaire createdPartenaire = partenaireService.createpartenaire(partenaire);
	                partenaireService.updateAutoIncrementValue("Partenaire", 1);
	            } else {
	                Partenaire updatedPartenaire = partenaireService.updatepartenaire(partenaire);
	            }
	        }
	        Partenariat updatedPartenariat = partenariatService.updatePartenariat(partenariat, idDeleg, /*idpartenaire,*/idcommune);
	        return ResponseEntity.ok().body(updatedPartenariat);
	    }



	    /*@PostMapping("/getpartbyid")
	    public ResponseEntity<Partenariat> getPartenariatById(@RequestBody Long id) {
	        Partenariat partenariat = partenariatService.getPartenariatById(id);
	        return ResponseEntity.ok().body(partenariat);
	    }*/

	    /* @PostMapping("/getpartbyid")
	    public ResponseEntity<Partenariat> getPartenariatById(@RequestBody RequestBodyObject requestBody) {
	        Long partenariatId = requestBody.getPartenariatId();
	        Partenariat partenariat = partenariatService.getPartenariatById(partenariatId);
	        return ResponseEntity.ok().body(partenariat);
	    }
*/

	    
	    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
	    @Transactional
	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<?> deletePartenariat(@PathVariable Long id) {
	        suivieService.deleteSuivieByPartenariatId(id);
	        partenariatService.supprimerPartenariat(id);
	        return ResponseEntity.ok().build();
	    }

	    @PostMapping("/bydomaine")
	    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
	    public ResponseEntity<List<Partenariat>> getPartenariatByDomaine(@RequestBody String domaine) {
	        List<Partenariat> partenariats = partenariatService.getPartenariatbydomaine(domaine);
	        return ResponseEntity.ok().body(partenariats);
	    }

	    @PostMapping("/bytype")
	    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
	    public ResponseEntity<List<Partenariat>> getPartenariatByType(@RequestBody String type) {
	        List<Partenariat> partenariats = partenariatService.getPartenariatbytype(type);
	        return ResponseEntity.ok().body(partenariats);
	    }

	    @PostMapping("/partenairesbyidpartenariat")
	    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
	    public ResponseEntity<List<Partenaire>> getPartenairesByPartenariatId(@RequestBody Long partenariatId) {
	        List<Partenaire> partenaires = partenariatService.getPartenairesByPartenariatId(partenariatId);
	        return ResponseEntity.ok(partenaires);
	    }

	   

	    @PostMapping("/partenariatsByPartenaireId")
	    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
	    public List<Partenariat> getPartenariatsByPartenaireId(@RequestBody Long partenaireId) {
	        return partenariatService.getPartenariatsByPartenaireId(partenaireId);
	    }

	    @GetMapping("/allpart")
	    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
	    public List<Partenariat> getAllPartenariats() {
	        return partenariatService.getAllPartenariats();
	    }
	    
	    
	}