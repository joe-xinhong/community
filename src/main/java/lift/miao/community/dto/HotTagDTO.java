package lift.miao.community.dto;

import lombok.Data;

/**
* @Description:    描述
* @Author:         Joe
* @CreateDate:     2019/9/28 21:28
*/
@Data
public class HotTagDTO implements Comparable{

    private String name;
    private Integer priority;

    @Override
    public int compareTo(Object o) {
        return this.getPriority()-((HotTagDTO) o).getPriority();
    }
}
