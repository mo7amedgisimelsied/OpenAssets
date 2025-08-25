package OpenAssets.demo.DTOs;

public class ModelDTO {
    private Integer id;
    private String previewPath;

    public ModelDTO(){

    }

    public ModelDTO(Integer id, String previewPath) {
        this.id = id;
        this.previewPath = previewPath;
    }

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
