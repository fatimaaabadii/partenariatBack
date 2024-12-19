package com.entraide.part.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entraide.part.entity.*;

import java.util.*;

public interface AttachmentRepository extends JpaRepository<Attachment, String>{

	void save(Set<Attachment> setatts);

	//List<Attachment> findById(Long idarticle);

	List<Attachment> findByIdpart(Long idpart);
	//List<Attachment> findByPartenariatId(Long idpart);

	void deleteByPartenariatId(Long idpart);
    
}
