package OpenAssets.demo.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "models")
public class ModelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_id")
    private Integer modelId;

    @Column(name = "model_title")
    private String modelTitle;

    @Column(name = "model_description")
    private String modelDescription;

    @Column(name = "creator")
    private String creator;

    @Column(name = "keywords")
    private String keywords;

    @Column(name = "preview_path")
    private String previewPath;

    @Column(name = "file_path")
    private String filePath;

    public ModelEntity(){

    }

    public ModelEntity(String modelTitle, String modelDescription, String creator, String keywords, String previewPath, String filePath) {
        this.modelTitle = modelTitle;
        this.modelDescription = modelDescription;
        this.creator = creator;
        this.keywords = keywords;
        this.previewPath = previewPath;
        this.filePath = filePath;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getModelTitle() {
        return modelTitle;
    }

    public void setModelTitle(String modelTitle) {
        this.modelTitle = modelTitle;
    }

    public String getModelDescription() {
        return modelDescription;
    }

    public void setModelDescription(String modelDescription) {
        this.modelDescription = modelDescription;
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
