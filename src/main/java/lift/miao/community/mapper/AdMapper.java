package lift.miao.community.mapper;

import java.util.List;
import lift.miao.community.model.Ad;
import lift.miao.community.model.AdExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdMapper {
    long countByExample(AdExample example);

    int deleteByExample(AdExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Ad record);

    int insertSelective(Ad record);

    List<Ad> selectByExample(AdExample example);

    Ad selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Ad record, @Param("example") AdExample example);

    int updateByExample(@Param("record") Ad record, @Param("example") AdExample example);

    int updateByPrimaryKeySelective(Ad record);

    int updateByPrimaryKey(Ad record);
}