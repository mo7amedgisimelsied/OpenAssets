package OpenAssets.demo.Entities;

import jakarta.persistence.*;

/// Entity class for 'vectors' table in the database,
/// each field maps to a column in the table,
/// the id is auto-generated for each new record.
@Entity
@Table(name = "vectors")
public class VectorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vector_id")
    private Integer vectorId;

    @Column(name = "vector_title")
    private String vectorTitle;

    @Column(name = "vector_description")
    private String vectorDescription;

    @Column(name = "creator")
    private String creator;

    @Column(name = "keywords")
    private String keywords;

    @Column(name = "preview_path")
    private String previewPath;

    @Column(name = "file_path")
    private String filePath;


    public VectorEntity(){

    }

    public VectorEntity(String vectorTitle, String vectorDescription, String creator, String keywords, String previewPath, String filePath) {
        this.vectorTitle = vectorTitle;
        this.vectorDescription = vectorDescription;
        this.creator = creator;
        this.keywords = keywords;
        this.previewPath = previewPath;
        this.filePath = filePath;
    }

    public Integer getVectorId() {
        return vectorId;
    }

    public void setVectorId(Integer vectorId) {
        this.vectorId = vectorId;
    }

    public String getVectorTitle() {
        return vectorTitle;
    }

    public void setVectorTitle(String vectorTitle) {
        this.vectorTitle = vectorTitle;
    }

    public String getVectorDescription() {
        return vectorDescription;
    }

    public void setVectorDescription(String vectorDescription) {
        this.vectorDescription = vectorDescription;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getPreviewPath() {
        return previewPath;
    }

    public void setPreviewPath(String previewPath) {
        this.previewPath = previewPath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
