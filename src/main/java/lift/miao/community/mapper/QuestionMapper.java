package lift.miao.community.mapper;

import lift.miao.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @Description:    问题接口
* @Author:         Joe
* @CreateDate:     2019/9/6 17:23
*/
@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from question limit #{offset},#{pageSize}")
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "pageSize")Integer pageSize);

    @Select("select count(1) from question")
    Integer count();
}
