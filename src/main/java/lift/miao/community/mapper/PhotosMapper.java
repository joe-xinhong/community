package lift.miao.community.mapper;

import java.util.List;
import lift.miao.community.model.Photos;
import lift.miao.community.model.PhotosExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PhotosMapper {
    long countByExample(PhotosExample example);

    int deleteByExample(PhotosExample example);

    int deleteByPrimaryKey(Integer pId);

    int insert(Photos record);

    int insertSelective(Photos record);

    List<Photos> selectByExampleWithRowbounds(PhotosExample example, RowBounds rowBounds);

    List<Photos> selectByExample(PhotosExample example);

    Photos selectByPrimaryKey(Integer pId);

    int updateByExampleSelective(@Param("record") Photos record, @Param("example") PhotosExample example);

    int updateByExample(@Param("record") Photos record, @Param("example") PhotosExample example);

    int updateByPrimaryKeySelective(Photos record);

    int updateByPrimaryKey(Photos record);
}