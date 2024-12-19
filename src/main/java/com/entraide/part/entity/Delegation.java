package com.entraide.part.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

import com.entraide.part.entity.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Delegation {
  
  @Id
  @GeneratedValue(
      strategy = GenerationType.IDENTITY
  )
  private Long id;
  @Column(nullable = false, columnDefinition = "varchar(255) DEFAULT ''")
  private String delegation;

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

public Long getId() {
	return id;
}

  
  private String fax;
  private String adresse;
public void setId(Long id) {
	this.id = id;
}

public String getDelegation() {
	return delegation;
}

public void setDelegation(String delegation) {
	this.delegation = delegation;
}

public String getTel() {
	return tel;
}

public void setTel(String tel) {
	this.tel = tel;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}


public String getCoordination() {
	return coordination;
}

public void setCoordination(String coordination) {
	this.coordination = coordination;
}


private String tel;
 private String email;
 private String coordination;

  //@Column(nullable = false, columnDefinition = "varchar(255) DEFAULT ''")
  //private String delegationAr;
  //@Column(nullable = false, columnDefinition = "INT DEFAULT 0")
  //private Integer idRegion;
  //@Column(nullable = false)
  //private int idRegionNv;
  //@Column(nullable = false, columnDefinition = "varchar(255) DEFAULT ''")
  //private String delegationFr;
  //@Column(nullable = false, columnDefinition = "varchar(255) DEFAULT ''")
  //private String delegationArr;

  // @OneToMany(mappedBy = "delegation")
  //private List<Commune> communes ;

  // @ManyToMany(mappedBy = "delegations")
  // private Set<Partenariat> partenariats = new HashSet<>();

  
  
  
  
  
  
  
  
  
  
  
  // @Transient
  // private List<Long> idCommunes;

  // Getters et setters pour idCommunes
  // public List<Long> getIdCommunes() {
  //  return idCommunes;
  // }

  // public void setIdCommunes(List<Long> idCommunes) {
  //this.idCommunes = idCommunes;
  // }
}
