package lift.miao.community.mapper;

import lift.miao.community.dto.QuestionQueryDTO;
import lift.miao.community.model.Question;

import java.util.List;
public interface QuestionExtMapper {

    int incView(Question record);

    int incCommentCount(Question record);

    List<Question> selectRelated(Question question);

    Integer countBySearch(QuestionQueryDTO questionQueryDTO);

    List<Question> selectBySearch(QuestionQueryDTO questionQueryDTO);
}