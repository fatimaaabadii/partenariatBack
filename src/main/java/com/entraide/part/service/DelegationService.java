package com.entraide.part.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entraide.part.entity.*;
import com.entraide.part.repository.*;

import java.util.*;

@Service
public class DelegationService {
    
    @Autowired
    private DelegationRepository delegationRepository;


    public Delegation createDelegation(Delegation newdelegation){

        return delegationRepository.save(newdelegation);
    }

    public Delegation updateDelegation(Delegation newdelegation){

        Optional<Delegation> updatedDelegation = delegationRepository.findById(newdelegation.getId());
        
        Delegation del = updatedDelegation.get();

        del.setDelegation(newdelegation.getDelegation());
        del.setEmail(newdelegation.getEmail());
        del.setTel(newdelegation.getTel());
        del.setCoordination(newdelegation.getCoordination());
        del.setAdresse(newdelegation.getAdresse());
        del.setFax(newdelegation.getFax());
        //del.setDelegationAr(newdelegation.getDelegationAr());
        //del.setDelegationArr(newdelegation.getDelegationArr());
        //del.setDelegationFr(newdelegation.getDelegationFr());
        //del.setIdRegion(newdelegation.getIdRegion());
        //del.setIdRegionNv(newdelegation.getIdRegionNv());
    
        return delegationRepository.save(del);
    }

    public Delegation getDelegationById(Long delegationId) {
        return delegationRepository.findById(delegationId)
                                 .orElseThrow(() -> new NoSuchElementException("delegation not found"));
    }

    public List<Delegation> getDelegationsById(List<Long> delegationIds) {
        return delegationRepository.findAllById(delegationIds);
    }
    
    public List<Delegation> getDelegationbyname(String delegation){

        List<Delegation> delegations = delegationRepository.findBydelegation(delegation);
        return delegations;
    }

    public void deletePartenariatById(Long partId) {
        Optional<Delegation> com = delegationRepository.findById(partId);
        if (com != null) {
            delegationRepository.deleteById(partId);
        } else {
            throw new IllegalArgumentException("Delegation not found");
        }
    }

	
	public List<Delegation> getAllDelegations() {
		
		return delegationRepository.findAll();
	}
}
