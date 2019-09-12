package lift.miao.community.service;

import lift.miao.community.dto.PaginationDTO;
import lift.miao.community.dto.QuestionDTO;
import lift.miao.community.exception.CustomizeErrorCode;
import lift.miao.community.exception.CustomizeException;
import lift.miao.community.mapper.QuestionMapper;
import lift.miao.community.mapper.UserMapper;
import lift.miao.community.model.Question;
import lift.miao.community.model.QuestionExample;
import lift.miao.community.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer pageSize) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        /*信息总条数*/
        Integer totalCount = (int)questionMapper.countByExample(new QuestionExample());
        if(totalCount % pageSize ==0){
            totalPage = totalCount / pageSize;
        }else {
            totalPage = totalCount / pageSize + 1;
        }
        if(page<1){
            page=1;
        }
        if(page>totalPage){
            page=totalPage;
        }
        paginationDTO.setPagination(totalPage,page);
        Integer offset = pageSize * (page-1);
        if(offset<0){
            offset=0;
        }
        List<Question> questionList = questionMapper.selectByExampleWithRowbounds(new QuestionExample(),new RowBounds(offset,pageSize));
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        if(questionList!=null&&questionList.size()>0){
            for (Question question : questionList) {
                User user = userMapper.selectByPrimaryKey(question.getCreator());
                QuestionDTO questionDTO = new QuestionDTO();
                BeanUtils.copyProperties(question,questionDTO);
                questionDTO.setUser(user);
                questionDTOList.add(questionDTO);
            }
            paginationDTO.setQuestionList(questionDTOList);
        }
        return paginationDTO;
    }

    public PaginationDTO list(Integer userId, Integer page, Integer pageSize) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        /*信息总条数*/
        Integer totalCount = (int)questionMapper.countByExample(questionExample);
        if(totalCount % pageSize ==0){
            totalPage = totalCount / pageSize;
        }else {
            totalPage = totalCount / pageSize + 1;
        }
        if(page<1){
            page=1;
        }
        if(page>totalPage){
            page=totalPage;
        }
        paginationDTO.setPagination(totalPage,page);
        Integer offset = pageSize * (page-1);
        QuestionExample questionExample1 = new QuestionExample();
        questionExample1.createCriteria().andCreatorEqualTo(userId);
        List<Question> questionList = questionMapper.selectByExampleWithRowbounds(questionExample1,new RowBounds(offset,pageSize));
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        if(questionList!=null&&questionList.size()>0){
            for (Question question : questionList) {
                User user = userMapper.selectByPrimaryKey(question.getCreator());
                QuestionDTO questionDTO = new QuestionDTO();
                BeanUtils.copyProperties(question,questionDTO);
                questionDTO.setUser(user);
                questionDTOList.add(questionDTO);
            }
            paginationDTO.setQuestionList(questionDTOList);
        }
        return paginationDTO;
    }

    /**
     *获取问题详情
     * @param id
     * @return
     */
    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if(question==null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }


    public void createOrUpdate(Question question) {
        if(question.getId()==null){
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        }else {
            //更新
            Question UpdateQuestion = new Question();
            UpdateQuestion.setGmtModified(System.currentTimeMillis());
            UpdateQuestion.setTitle(question.getTitle());
            UpdateQuestion.setDescription(question.getDescription());
            UpdateQuestion.setTag(question.getTag());
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria().andIdEqualTo(question.getId());
            int updated =questionMapper.updateByExampleSelective(UpdateQuestion,questionExample);
            if(updated!=1){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }
}
