package com.entraide.part.controller;

import java.util.ArrayList;
//import java.awt.PageAttributes.MediaType;
//import java.net.http.HttpHeaders;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

//import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import com.entraide.part.entity.Attachment;
import com.entraide.part.service.PartenariatService;
import com.entraide.part.service.AttachmentService;
//import ma.entraide.communication.service.ByteArrayResource;
import com.entraide.part.service.DelegationService;



import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;


import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.awt.*;
@CrossOrigin("*")
@RestController
@RequestMapping("/attachments")
public class AttachmentController {

	
	    @Autowired
	    DelegationService delegationService;
	 
	 
	    @Autowired
	    PartenariatService articleService;

	    @Autowired
	    AttachmentService attachmentService;


	    @PutMapping("/create/{idpart}")
	    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
	    public ResponseEntity<List<ResponseData>> createAttachment(@PathVariable Long idpart,@RequestParam("files") List<MultipartFile> files) throws Exception {
	        List<ResponseData> responseDataList = new ArrayList<>();
	        for (MultipartFile file : files) {
	            Attachment attachment = attachmentService.createAttachment(file, idpart);
	            String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
	                    .path("/download/")
	                    .path(attachment.getId())
	                    .toUriString();

	            responseDataList.add(new ResponseData(attachment.getFileName(),
	                    downloadUrl,
	                    file.getContentType(),
	                    file.getSize()));
	        }
	        return new ResponseEntity<>(responseDataList, HttpStatus.CREATED);
	    }
	    
	    /* public ResponseData  createAttachment(@RequestParam MultipartFile file) throws Exception
	                                                         /*@RequestBody List<Long> idDeleg,
	                                                         @RequestBody List<Long> idpartenaire,
	                                                         @RequestBody List<Long> idcommune*/
	    /*{

	    	Attachment attachment = null;
	        String downloadURl = "";
	        attachment = attachmentService.createAttachment(file);
	        downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/download/")
	                .path(attachment.getId())
	                .toUriString();

	        return new ResponseData(attachment.getFileName(),
	                downloadURl,
	                file.getContentType(),
	                file.getSize());
	      
	    	 //Attachment createdAttachment= attachmentService.createAttachment(attachment);
	        // return new ResponseEntity<>(createdAttachment, HttpStatus.CREATED);
	    }*/
	    
	    

	    
	    @GetMapping("/download/{fileId}")
	    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
	        Attachment attachment = null;
	        attachment = attachmentService.getAttachmentById(fileId);
	        return  ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(attachment.getFileType()))
	                .header(HttpHeaders.CONTENT_DISPOSITION,
	                        "attachment; filename=\"" + attachment.getFileName()
	                + "\"")
	                .body(new ByteArrayResource(attachment.getData()));
	    }

		
	    
	    @GetMapping("/by-article/{idpart}")
	    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
	    public ResponseEntity<List<Attachment>> getAttachmentsByArticleId(@PathVariable Long idpart) {
	        List<Attachment> attachments = attachmentService.getAttachmentsByPartenariatId(idpart);
	        return ResponseEntity.ok(attachments);
	    }
	    

	   /* @PutMapping("/update/{id}")
	    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
	    public ResponseEntity<Attachment> updatePartenariat(@PathVariable Long idArticle,
	                                                         @RequestBody Attachment attach
	                                                         /*@RequestParam List<Long> idDeleg,
	                                                         @RequestParam List<Long> idpartenaire,
	                                                         @RequestParam List<Long> idcommune*/ 

	    /*	Attachment updatedAttach = attachmentService.updateAttachment(attach);
	         return new ResponseEntity<>(updatedAttach, HttpStatus.OK);
	    }

	    /*@PostMapping("/getpartbyid")
	    public ResponseEntity<Partenariat> getPartenariatById(@RequestBody Long id) {
	        Partenariat partenariat = partenariatService.getPartenariatById(id);
	        return ResponseEntity.ok().body(partenariat);
	    }*/

	    


	    @Transactional
	    @DeleteMapping("/delete/{id}")
	    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
	    public ResponseEntity<?> deleteAttachmentById(@PathVariable String id) {
	      
	        attachmentService.deleteAttachmentById(id);
	        return ResponseEntity.ok().build();
	    }

	   
	   /* @GetMapping("/allAttachments")
	    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
	    public List<Attachment> getAllAttachments() {
	        return attachmentService.getAllAttachments();
	    }*/

	    

	
}

