package com.entraide.part.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;

import org.hibernate.annotations.CreationTimestamp;

import com.entraide.part.entity.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Partenariat {

    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(nullable = false)
    private String partenariatName;
    private String populationCible;
   // @Column(nullable = false, columnDefinition = "varchar(255) DEFAULT ''")
    //private String province;
  
    public String getPopulationCible() {
		return populationCible;
	}



	public void setPopulationCible(String populationCible) {
		this.populationCible = populationCible;
	}



	private String domaine;
    private String coordination;
  
    public String getCoordination() {
		return coordination;
	}



	public void setCoordination(String coordination) {
		this.coordination = coordination;
	}



	private String numero;
   
    private String typeCentre;
 
    private String dureepartenariat;
  
   
   
    private String type;
    @Column(nullable = false, columnDefinition = "DOUBLE DEFAULT 0")
    private double estimatioFinancier;
    private String dateSignature;
    @Column(nullable = false, columnDefinition = "varchar(255) DEFAULT ''")
    private String signataire;
   
    //private String projetOperationnel;
   //@Column(nullable = false, columnDefinition = "varchar(255) DEFAULT ''")
  //  private String observation;
   @Column(columnDefinition = "varchar(255) DEFAULT ''")
    private String programme_INDH;
    private int duree;
   // private int idStructure;
   // private int idLocal;
    @Column(nullable = false)
    private String contribEn;
   
    @Column(nullable = false, columnDefinition = "DOUBLE DEFAULT 0")
    private double montant_global;
   private String indh;
  
    public String getIndh() {
	return indh;
}



public void setIndh(String indh) {
	this.indh = indh;
}



	@Column(nullable = false)
    private String date_lancement;
    @Column(nullable = false)
    private String date_achevement;
   
    
    @CreationTimestamp
    private LocalDateTime dateSaisie;


    @ManyToMany
    @JoinTable(
        name = "Delegation_Partenariat",
        joinColumns = @JoinColumn(name = "partenariat_id"),
        inverseJoinColumns = @JoinColumn(name = "delegation_id")
    )
    private Set<Delegation> delegations = new HashSet<>();
    

    @ManyToMany
    @JoinTable(
        name = "Partenaire_Partenariat",
        joinColumns = @JoinColumn(name = "partenariat_id"),
        inverseJoinColumns = @JoinColumn(name = "partenaire_id")
    )
    private Set<Partenaire> partenaires = new HashSet<>();
    

    

    @ManyToMany
    @JoinTable(
        name = "Partenariat_Commune",
        joinColumns = @JoinColumn(name = "partenariat_id"),
        inverseJoinColumns = @JoinColumn(name = "commune_id")
    )
   

    @Transient
    private List<Long> idCommunes;

    @Transient
    private List<Long> IdPartenaires;

    @Transient
    private List<Long> idDelegations;

   


    public String getAnneeFin() {
		return anneeFin;
	}



	public void setAnneeFin(String anneeFin) {
		this.anneeFin = anneeFin;
	}



	private String anneeFin;
  



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getPartenariatName() {
		return partenariatName;
	}



	public void setPartenariatName(String partenariatName) {
		this.partenariatName = partenariatName;
	}



	public String getDomaine() {
		return domaine;
	}



	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}



	public String getNumero() {
		return numero;
	}



	public void setNumero(String numero) {
		this.numero = numero;
	}



	public String getTypeCentre() {
		return typeCentre;
	}



	public void setTypeCentre(String typeCentre) {
		this.typeCentre = typeCentre;
	}



	


	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public double getEstimatioFinancier() {
		return estimatioFinancier;
	}



	public void setEstimatioFinancier(double estimatioFinancier) {
		this.estimatioFinancier = estimatioFinancier;
	}



	public String getDateSignature() {
		return dateSignature;
	}



	public void setDateSignature(String dateSignature) {
		this.dateSignature = dateSignature;
	}



	public String getSignataire() {
		return signataire;
	}



	public void setSignataire(String signataire) {
		this.signataire = signataire;
	}



	public String getProgramme_INDH() {
		return programme_INDH;
	}



	public void setProgramme_INDH(String programme_INDH) {
		this.programme_INDH = programme_INDH;
	}



	public int getDuree() {
		return duree;
	}



	public void setDuree(int duree) {
		this.duree = duree;
	}



	public String getContribEn() {
		return contribEn;
	}



	public void setContribEn(String contribEn) {
		this.contribEn = contribEn;
	}



	public double getMontant_global() {
		return montant_global;
	}



	public void setMontant_global(double montant_global) {
		this.montant_global = montant_global;
	}



	public String getDate_lancement() {
		return date_lancement;
	}



	public void setDate_lancement(String date_lancement) {
		this.date_lancement = date_lancement;
	}



	public String getDate_achevement() {
		return date_achevement;
	}



	public void setDate_achevement(String date_achevement) {
		this.date_achevement = date_achevement;
	}



	public LocalDateTime getDateSaisie() {
		return dateSaisie;
	}



	public void setDateSaisie(LocalDateTime dateSaisie) {
		this.dateSaisie = dateSaisie;
	}



	public Set<Delegation> getDelegations() {
		return delegations;
	}



	public void setDelegations(Set<Delegation> delegations) {
		this.delegations = delegations;
	}



	public Set<Partenaire> getPartenaires() {
		return partenaires;
	}



	public void setPartenaires(Set<Partenaire> partenaires) {
		this.partenaires = partenaires;
	}



	



	



	public List<Long> getIdCommunes() {
		return idCommunes;
	}



	public void setIdCommunes(List<Long> idCommunes) {
		this.idCommunes = idCommunes;
	}



	public List<Long> getIdPartenaires() {
		return IdPartenaires;
	}



	public void setIdPartenaires(List<Long> idPartenaires) {
		IdPartenaires = idPartenaires;
	}



	public List<Long> getIdDelegations() {
		return idDelegations;
	}



	public void setIdDelegations(List<Long> idDelegations) {
		this.idDelegations = idDelegations;
	}



	



	


	



	public String getDureepartenariat() {
		return dureepartenariat;
	}



	public void setDureepartenariat(String dureepartenariat) {
		this.dureepartenariat = dureepartenariat;
	}
    
}