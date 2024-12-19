package com.entraide.part.controller;

import org.springframework.web.bind.annotation.RestController;

import com.entraide.part.entity.*;
import com.entraide.part.service.*;

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

@CrossOrigin("*")
@RestController
@RequestMapping("/delegation")
public class DelegationController {
    
    @Autowired
    private DelegationService delegationService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
    public ResponseEntity<Delegation> createDelegation(@RequestBody Delegation delegation) {

        Delegation createdDelegation = delegationService.createDelegation(delegation);
        return new ResponseEntity<>(createdDelegation, HttpStatus.CREATED);
    }

	    @PutMapping("/update/{id}")
	    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
	    public ResponseEntity<Delegation> updateDelegation(@PathVariable Long id, @RequestBody Delegation delegation) {
	        
	        Delegation updatedDelegation = delegationService.updateDelegation(delegation);
	        return new ResponseEntity<>(updatedDelegation, HttpStatus.OK);
	    }


    @GetMapping("/getDelegationById")
    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
    public ResponseEntity<Delegation> getDelegationById(@RequestBody Long delegationId) {
        Delegation delegation = delegationService.getDelegationById(delegationId);
        return new ResponseEntity<>(delegation, HttpStatus.OK);
    }

    @GetMapping("/getDelegationByName")
    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
    public ResponseEntity<List<Delegation>> getDelegationByName(@RequestBody String delegationName) {
        List<Delegation> delegations = delegationService.getDelegationbyname(delegationName);
        return new ResponseEntity<>(delegations, HttpStatus.OK);
    }

    @DeleteMapping("/{delegationId}")
    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
    public ResponseEntity<String> deleteDelegationById(@PathVariable Long delegationId) {
        delegationService.deletePartenariatById(delegationId);
        return new ResponseEntity<>("Delegation deleted successfully", HttpStatus.OK);
    }
    
    @GetMapping("/getDelegations")
    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
   	public ResponseEntity<List<Delegation>> findAllDelegations(){
   	        return new ResponseEntity<>(delegationService.getAllDelegations(), HttpStatus.OK);
   	    }

}