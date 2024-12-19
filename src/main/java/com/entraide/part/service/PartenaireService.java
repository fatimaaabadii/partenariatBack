package com.entraide.part.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.entraide.part.entity.Partenaire;
import com.entraide.part.repository.PartenaireRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import org.springframework.jdbc.core.JdbcTemplate;
@Service
public class PartenaireService {
    
    @Autowired
    private PartenaireRepository partenaireRepository;

    public Partenaire createpartenaire(Partenaire partenaire){

        return partenaireRepository.save(partenaire);
    }

    public Partenaire updatepartenaire(Partenaire newPartenaire){

        Optional<Partenaire> updatedPartenaire = partenaireRepository.findById(newPartenaire.getId());
        
        Partenaire parten = updatedPartenaire.get();

        parten.setAdresse(newPartenaire.getAdresse());
        parten.setPartenaire(newPartenaire.getPartenaire());
        parten.setEmail(newPartenaire.getEmail());
        parten.setEstimContribFinanc(newPartenaire.getEstimContribFinanc());
        parten.setContribution(newPartenaire.getContribution());
        
        parten.setFax(newPartenaire.getFax());
        parten.setTel(newPartenaire.getTel());
        parten.setType(newPartenaire.getType());
        parten.setWeb(newPartenaire.getWeb());
        parten.setSoustype(newPartenaire.getSoustype());
        return partenaireRepository.save(parten);

    }

    public Partenaire getpartenaireById(Long partenaireId) {
        return partenaireRepository.findById(partenaireId)
                                 .orElseThrow(() -> new NoSuchElementException("partenaire not found"));
    }

    public List<Partenaire> getPartenairesById(List<Long> partenaireIds) {
        return partenaireRepository.findAllById(partenaireIds);
    }

    public List<Partenaire> getPartenairebyname(String partenaire){

        List<Partenaire> partenaires = partenaireRepository.findByPartenaire(partenaire);
        //System.err.println(partenaires);
        return partenaires;
     
    }

    public List<Partenaire> getAllPartenaires() {
        return partenaireRepository.findAll();
    }

    public void deletePartenaireById(Long partenId) {
        Optional<Partenaire> parten = partenaireRepository.findById(partenId);
        if (parten != null) {
            partenaireRepository.deleteById(partenId);
        } else {
            throw new IllegalArgumentException("Partenaire not found");
        }
    }

    public Page<Partenaire> getPartenaires(PageRequest pageable) {
        return partenaireRepository.findAll(pageable);
    }

	public List<Partenaire> getAllPartenaire() {
		// TODO Auto-generated method stub
		return partenaireRepository.findAll();
	}
	 @Autowired
	    private JdbcTemplate jdbcTemplate;

	    public Long getLastAutoIncrementValue() {
	        String sql = "SELECT AUTO_INCREMENT AS next_id " +
	                     "FROM INFORMATION_SCHEMA.TABLES " +
	                     "WHERE TABLE_SCHEMA = 'partenariat' " +
	                     "AND TABLE_NAME = 'partenaire'";
	        Long lastId = jdbcTemplate.queryForObject(sql, Long.class);
	        return lastId != null ? lastId : 0L;
	    }
	    public Long getLastAutoIncrementValue(Partenaire partenaire) {
	        if (partenaire.getId() == null) {

	            Long newId = getLastAutoIncrementValue();
	            partenaire.setId(newId);
	        }
	        return partenaire.getId();
	    }

	    
	    
	    public void updateAutoIncrementValue(String tableName, long value) {
	        String sql = "ALTER TABLE " + tableName + " AUTO_INCREMENT = " + value;
	        
	        jdbcTemplate.execute(sql);
	    }
	        
	    


}