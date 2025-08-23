package OpenAssets.demo.Entities;

import com.fasterxml.jackson.annotation.JsonRawValue;
import jakarta.persistence.*;

@Entity
@Table(name = "icons")
public class Icons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "icon_id")
    private Integer iconId;

    @Column(name = "icon_title")
    private String iconTitle;

    @Column(name = "icon_description")
    private String iconDescription;

    @Column(name = "svg_code")
    @JsonRawValue
    private String svgCode;

    private String creator;

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getSvgCode() {
        return svgCode;
    }

    public void setSvgCode(String svgCode) {
        this.svgCode = svgCode;
    }

    public String getIconDescription() {
        return iconDescription;
    }

    public void setIconDescription(String iconDescription) {
        this.iconDescription = iconDescription;
    }

    public String getIconTitle() {
        return iconTitle;
    }

    public void setIconTitle(String iconTitle) {
        this.iconTitle = iconTitle;
    }

    public Integer getIconId() {
        return iconId;
    }

    public void setIconId(Integer iconId) {
        this.iconId = iconId;
    }
}
