package OpenAssets.demo.DTOs;
/// Data Transfer Object (DTO) for Model entity to send only the necessary data to the client
public class ModelDTO {
    private Integer id;
    private String previewPath;

    public ModelDTO(){
    }
    /// Constructor to initialize ModelDTO with id and previewPath
    public ModelDTO(Integer id, String previewPath) {
        this.id = id;
        this.previewPath = previewPath;
    }

    /// Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPreviewPath() {
        return previewPath;
    }

    public void setPreviewPath(String previewPath) {
        this.previewPath = previewPath;
    }
}
