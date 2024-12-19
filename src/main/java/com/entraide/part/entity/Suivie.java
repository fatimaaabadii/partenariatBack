package com.entraide.part.entity;

import java.math.BigDecimal;

import com.entraide.part.entity.Partenariat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Suivie {
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getEtatAvancement() {
		return etatAvancement;
	}

	public void setEtatAvancement(BigDecimal etatAvancement) {
		this.etatAvancement = etatAvancement;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getProjetOperationel() {
		return projetOperationel;
	}

	public void setProjetOperationel(String projetOperationel) {
		this.projetOperationel = projetOperationel;
	}

	public String getObjectifAtteints() {
		return objectifAtteints;
	}

	public void setObjectifAtteints(String objectifAtteints) {
		this.objectifAtteints = objectifAtteints;
	}

	public Partenariat getPartenariat() {
		return partenariat;
	}

	public void setPartenariat(Partenariat partenariat) {
		this.partenariat = partenariat;
	}

	public Long getIdpart() {
		return idpart;
	}

	public void setIdpart(Long idpart) {
		this.idpart = idpart;
	}

	@Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private Long id;
    public String getAvenant() {
		return avenant;
	}

	public void setAvenant(String avenant) {
		this.avenant = avenant;
	}

	private BigDecimal etatAvancement;
    private String etat;
    private String commentaire;
    private String projetOperationel;
    @Column(columnDefinition = "LONGTEXT")
    private String objectifAtteints;
    private String dateSuivi;
    private String avenant;

    public String getDateSuivi() {
		return dateSuivi;
	}

	public void setDateSuivi(String dateSuivi) {
		this.dateSuivi = dateSuivi;
	}

	@ManyToOne
    @JoinColumn(name = "partenariat_id")
    private Partenariat partenariat;

    @Transient
    private Long idpart;
}