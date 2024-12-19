package com.entraide.part.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entraide.part.controller.*;
import com.entraide.part.entity.*;
import com.entraide.part.repository.*;
import com.entraide.part.service.*;

import jakarta.transaction.Transactional;

import java.util.*;

@Service
public class PartenariatService {
    
	@Autowired
    private PartenariatRepository partenariatRepository;

    @Autowired 
    private DelegationService delegationService;

    @Autowired
    private PartenaireService partenaireService;

    
    @Autowired
    private PartenaireRepository partenaireRepository;
   

    public Partenariat createPartenariat(Partenariat partenariat, List<Long> idDeleg, /*List<Long> idpartenaire,*/ List<Long> idcommune){

        List<Delegation> delegs = delegationService.getDelegationsById(idDeleg);
        Set<Delegation> setdelegs = new HashSet<>(delegs);
        partenariat.setDelegations(setdelegs);

        /*List<Partenaire> partens = partenaireService.getPartenairesById(idpartenaire);
        Set<Partenaire> setpartens = new HashSet<>(partens);
        partenariat.setPartenaires(setpartens);*/

       // List<Commune> coms = communeService.getCommunesById(idcommune);
       // Set<Commune> setcoms = new HashSet<>(coms);
        //partenariat.setCommunes(setcoms);

       
        

        return partenariatRepository.save(partenariat);
    }

    public Partenariat updatePartenariat(Partenariat partenariat, List<Long> idDeleg, /*List<Long> idpartenaire,*/List<Long> idcommune){

       Optional<Partenariat> updatedPartenariat = partenariatRepository.findById(partenariat.getId());
        
        Partenariat part = updatedPartenariat.get();

        part.setPartenariatName(partenariat.getPartenariatName());
        part.setDomaine(partenariat.getDomaine());
       
        part.setContribEn(partenariat.getContribEn());
        part.setCoordination(partenariat.getCoordination());
        part.setDateSaisie(partenariat.getDateSaisie());
        part.setDateSignature(partenariat.getDateSignature());
        part.setDate_achevement(partenariat.getDate_achevement());
        part.setDate_lancement(partenariat.getDate_lancement());
        
        part.setDuree(partenariat.getDuree()); 
        part.setPopulationCible(partenariat.getPopulationCible());
        List<Delegation> delegs = delegationService.getDelegationsById(idDeleg);
        Set<Delegation> setdelegs = new HashSet<>(delegs);
        part.setDelegations(setdelegs);
        part.setAnneeFin(partenariat.getAnneeFin());
        part.setPartenaires(partenariat.getPartenaires());
        part.setEstimatioFinancier(partenariat.getEstimatioFinancier());
        part.setDureepartenariat(partenariat.getDureepartenariat());
        part.setMontant_global(partenariat.getMontant_global());
        part.setTypeCentre(partenariat.getTypeCentre());
        part.setIndh(partenariat.getIndh());
       // List<Partenaire> partens = partenaireService.getPartenairesById(idpartenaire);
       // Set<Partenaire> setpartens = new HashSet<>(partens);
       // part.setPartenaires(setpartens);
       //// List<Commune> coms = communeService.getCommunesById(idcommune);
      
       // Set<Commune> setcoms = new HashSet<>(coms);
        
        //part.setCommunes(setcoms);
       

        
        //List<Commune> coms = communeService.getCommunesById(idcommune);
       /// Set<Commune> setcoms = new HashSet<>(coms);
       // part.setCommunes(setcoms);

        

        return partenariatRepository.save(part);
    }

    public Partenariat getPartenariatById(Long partenariatId) {
        return partenariatRepository.findById(partenariatId)
                                 .orElseThrow(() -> new NoSuchElementException("partenariat not found"));
    }

    public List<Partenariat> getPartenariatbydomaine(String domaine){

        List<Partenariat> partenariats = partenariatRepository.findByDomaine(domaine);
        return partenariats;
    }

    public List<Partenariat> getPartenariatbytype(String type){

        List<Partenariat> partenariats = partenariatRepository.findByType(type);
        return partenariats;
    }

    
    public List<Partenariat> getPartenariatsByPartenaireId(Long partenaireId) {
        return partenariatRepository.findByPartenairesId(partenaireId);
    }

    public List<Partenaire> getPartenairesByPartenariatId(Long partenariatId) {
        Partenariat partenariat = partenariatRepository.findById(partenariatId)
                .orElseThrow(() -> new NoSuchElementException("Partenariat not found"));
        System.out.println(partenariat);
        System.out.println(partenariat.getIdPartenaires());
        List<Partenaire> list = new ArrayList<>(partenariat.getPartenaires());
        return list;
    }

    public List<Partenariat> getAllPartenariats() {
        return partenariatRepository.findAll();
    }

    @Transactional
    public void supprimerPartenariat(Long idPartenariat) {
        Partenariat partenariat = partenariatRepository.findById(idPartenariat)
                .orElseThrow(() -> new RuntimeException("Partenariat non trouvé avec l'ID : " + idPartenariat));
        Set<Partenaire> partenaires = partenariat.getPartenaires();
        for (Partenaire partenaire : partenaires) {
            //partenaire.getPartenariats().remove(partenariat);
            partenaireRepository.delete(partenaire);
        }
        partenariatRepository.delete(partenariat);
        for (Partenaire partenaire : partenaires) {
            Long id = partenaire.getId();
            partenaireService.deletePartenaireById(id);
        }
    }
   
}