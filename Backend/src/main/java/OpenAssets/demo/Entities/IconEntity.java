package OpenAssets.demo.Entities;

import jakarta.persistence.*;

/// Entity class for 'icons' table in the database,
/// each field maps to a column in the table,
/// the id is auto-generated for each new record.
@Entity
@Table(name = "icons")
public class IconEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "icon_id")
    private Integer iconId;

    @Column(name = "icon_title")
    private String iconTitle;

    @Column(name = "icon_description")
    private String iconDescription;

    @Column(name = "svg_code")
    private String svgCode;

    private String creator;

    @Column(name = "keywords")
    private String keywords;

    public IconEntity(){

    }

    public IconEntity(String iconTitle, String iconDescription, String creator, String keywords, String svgCode) {
        this.iconTitle = iconTitle;
        this.iconDescription = iconDescription;
        this.creator = creator;
        this.keywords = keywords;
        this.svgCode = svgCode;
    }

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

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
