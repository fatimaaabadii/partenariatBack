package com.entraide.part.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Partenaire {
    
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private Long id;
    
    
   
    private String contribution;
    
    @Column(nullable = false, columnDefinition = "int(11) DEFAULT 0")
    private int idPartenariat;
    
    
    private String estimContribFinanc;

    public String getContribution() {
		return contribution;
	}

	public void setContribution(String contribution) {
		this.contribution = contribution;
	}

	public int getIdPartenariat() {
		return idPartenariat;
	}

	public void setIdPartenariat(int idPartenariat) {
		this.idPartenariat = idPartenariat;
	}

	public String getEstimContribFinanc() {
		return estimContribFinanc;
	}

	public void setEstimContribFinanc(String estimContribFinanc) {
		this.estimContribFinanc = estimContribFinanc;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPartenaire() {
		return partenaire;
	}

	public void setPartenaire(String partenaire) {
		this.partenaire = partenaire;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Column(nullable = false, columnDefinition = "varchar(255) DEFAULT ''")
    private String partenaire;

    @Column(nullable = false, columnDefinition = "varchar(255) DEFAULT ''")
    private String type;

    @Column(nullable = false, columnDefinition = "varchar(200) DEFAULT ''")
    private String tel;

    @Column(nullable = false, columnDefinition = "varchar(40) DEFAULT ''")
    private String fax;

    @Column(nullable = false, columnDefinition = "varchar(255) DEFAULT ''")
    private String adresse;

    @Column(nullable = false, columnDefinition = "varchar(70) DEFAULT ''")
    private String email;

    @Column(nullable = false, columnDefinition = "varchar(70) DEFAULT ''")
    private String web;
    
    private String soustype;

	public String getSoustype() {
		return soustype;
	}

	public void setSoustype(String soustype) {
		this.soustype = soustype;
	}

    // @Column(nullable = true, columnDefinition = "DOUBLE DEFAULT 0")
    // private Double estimContribFinanc;

    // @Column( nullable = true, columnDefinition = "varchar(255) DEFAULT ' '")
    // private String contribution;

    // @Column(nullable = true, columnDefinition = "int(20) DEFAULT 0")
    // private int apport;

    //@Column(nullable = true, columnDefinition = "int(11) DEFAULT 0")
    //private int idPartenariat;

    // @Column(nullable = true, columnDefinition = "DOUBLE DEFAULT 0")
    // private Double estimEquipement;

    //@Column(nullable = true, columnDefinition = "DOUBLE DEFAULT 0")
    //private Double estimConstruction;

    //@Column(nullable = true, columnDefinition = "DOUBLE DEFAULT 0")
    //  private Double estimEncadrement;

    // @Column(nullable = true, columnDefinition = "DOUBLE DEFAULT 0")
    // private Double estimLocal;

    // @Column(nullable = true, columnDefinition = "DOUBLE DEFAULT 0")
    //private Double estimAutre;


 //  @ManyToMany(mappedBy = "partenaires")
 //  private Set<Partenariat> partenariats = new HashSet<>();

}