package lift.miao.community.model;

import java.io.Serializable;

/**
 * ad
 * @author 
 */
public class Ad implements Serializable {
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 链接地址
     */
    private String url;

    /**
     * 图片
     */
    private String image;

    /**
     * 开始时间
     */
    private Long gmtStart;

    /**
     * 结束时间
     */
    private Long gmtEnd;

    private Long gmtCreate;

    private Long gmtModified;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 摆放位置
     */
    private String pos;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getGmtStart() {
        return gmtStart;
    }

    public void setGmtStart(Long gmtStart) {
        this.gmtStart = gmtStart;
    }

    public Long getGmtEnd() {
        return gmtEnd;
    }

    public void setGmtEnd(Long gmtEnd) {
        this.gmtEnd = gmtEnd;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Ad other = (Ad) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
            && (this.getImage() == null ? other.getImage() == null : this.getImage().equals(other.getImage()))
            && (this.getGmtStart() == null ? other.getGmtStart() == null : this.getGmtStart().equals(other.getGmtStart()))
            && (this.getGmtEnd() == null ? other.getGmtEnd() == null : this.getGmtEnd().equals(other.getGmtEnd()))
            && (this.getGmtCreate() == null ? other.getGmtCreate() == null : this.getGmtCreate().equals(other.getGmtCreate()))
            && (this.getGmtModified() == null ? other.getGmtModified() == null : this.getGmtModified().equals(other.getGmtModified()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getPos() == null ? other.getPos() == null : this.getPos().equals(other.getPos()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getImage() == null) ? 0 : getImage().hashCode());
        result = prime * result + ((getGmtStart() == null) ? 0 : getGmtStart().hashCode());
        result = prime * result + ((getGmtEnd() == null) ? 0 : getGmtEnd().hashCode());
        result = prime * result + ((getGmtCreate() == null) ? 0 : getGmtCreate().hashCode());
        result = prime * result + ((getGmtModified() == null) ? 0 : getGmtModified().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getPos() == null) ? 0 : getPos().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", url=").append(url);
        sb.append(", image=").append(image);
        sb.append(", gmtStart=").append(gmtStart);
        sb.append(", gmtEnd=").append(gmtEnd);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append(", status=").append(status);
        sb.append(", pos=").append(pos);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}