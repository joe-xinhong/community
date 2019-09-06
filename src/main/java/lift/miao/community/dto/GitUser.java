package lift.miao.community.dto;
/**
* @Description:    码云授权用户信息
* @Author:         Joe
* @CreateDate:     2019/9/5 16:58
*/
public class GitUser {

    private String name;

    private Long id;

    private String bio;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "GitUser{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", bio='" + bio + '\'' +
                '}';
    }
}