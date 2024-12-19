package com.entraide.part.entity;
import jakarta.persistence.Lob;

import org.hibernate.annotations.GenericGenerator;

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
@Entity
@Data

public class Attachment {
	@Id
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String fileName;
    
    
    

	

	@ManyToOne
    @JoinColumn(name = "Partenariat")
    private Partenariat partenariat;
    
    
   

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

	//@Transient
    private Long idpart;
    public Attachment(String id) {
		super();
		this.id = id;
	}

	public Attachment() {
		super();
	}

	public Attachment(String id, String fileName, String filePath, String fileType, byte[] data) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileType = fileType;
		this.data = data;
	}

	private String filePath;
   
    public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	private String fileType;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] data;

    public Attachment(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }
   /* @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;*/

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/*public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}*/
}




