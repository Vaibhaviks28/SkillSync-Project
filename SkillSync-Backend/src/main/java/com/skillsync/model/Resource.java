package com.skillsync.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "resources")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String type;   
    private String url;

    private String folderName;   

    @Column(length = 100000)
    private byte[] resourceFile; 

    @Column(nullable = false)
    private Boolean isCompleted = false;   //  NEW FLAG

    @ManyToOne
    @JoinColumn(name = "skill_id")
    @JsonIgnoreProperties("resources")
    private Skill skill;

    public Resource() {}

    public Resource(Long id, String title, String type, String url, String folderName,
                    Skill skill, byte[] resourceFile, Boolean isCompleted) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.url = url;
        this.folderName = folderName;
        this.skill = skill;
        this.resourceFile = resourceFile;
        this.isCompleted = isCompleted;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getFolderName() { return folderName; }
    public void setFolderName(String folderName) { this.folderName = folderName; }

    public byte[] getResourceFile() { return resourceFile; }
    public void setResourceFile(byte[] resourceFile) { this.resourceFile = resourceFile; }

    public Boolean getIsCompleted() { return isCompleted; }
    public void setIsCompleted(Boolean isCompleted) { this.isCompleted = isCompleted; }

    public Skill getSkill() { return skill; }
    public void setSkill(Skill skill) { this.skill = skill; }
}
