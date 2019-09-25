package lift.miao.community.model;

import java.io.Serializable;

public class Photos implements Serializable {
    private Integer pId;

    private String pName;

    private String pUrl;

    private Long createTime;

    private Long lastTime;

    private Integer userId;

    private Integer viewCount;

    private Integer likeCount;

    private static final long serialVersionUID = 1L;

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName == null ? null : pName.trim();
    }

    public String getpUrl() {
        return pUrl;
    }

    public void setpUrl(String pUrl) {
        this.pUrl = pUrl == null ? null : pUrl.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getLastTime() {
        return lastTime;
    }

    public void setLastTime(Long lastTime) {
        this.lastTime = lastTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
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
        Photos other = (Photos) that;
        return (this.getpId() == null ? other.getpId() == null : this.getpId().equals(other.getpId()))
            && (this.getpName() == null ? other.getpName() == null : this.getpName().equals(other.getpName()))
            && (this.getpUrl() == null ? other.getpUrl() == null : this.getpUrl().equals(other.getpUrl()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastTime() == null ? other.getLastTime() == null : this.getLastTime().equals(other.getLastTime()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getViewCount() == null ? other.getViewCount() == null : this.getViewCount().equals(other.getViewCount()))
            && (this.getLikeCount() == null ? other.getLikeCount() == null : this.getLikeCount().equals(other.getLikeCount()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getpId() == null) ? 0 : getpId().hashCode());
        result = prime * result + ((getpName() == null) ? 0 : getpName().hashCode());
        result = prime * result + ((getpUrl() == null) ? 0 : getpUrl().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastTime() == null) ? 0 : getLastTime().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getViewCount() == null) ? 0 : getViewCount().hashCode());
        result = prime * result + ((getLikeCount() == null) ? 0 : getLikeCount().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pId=").append(pId);
        sb.append(", pName=").append(pName);
        sb.append(", pUrl=").append(pUrl);
        sb.append(", createTime=").append(createTime);
        sb.append(", lastTime=").append(lastTime);
        sb.append(", userId=").append(userId);
        sb.append(", viewCount=").append(viewCount);
        sb.append(", likeCount=").append(likeCount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}