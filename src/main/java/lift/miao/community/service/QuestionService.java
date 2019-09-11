package lift.miao.community.service;

import lift.miao.community.dto.PaginationDTO;
import lift.miao.community.dto.QuestionDTO;
import lift.miao.community.mapper.QuestionMapper;
import lift.miao.community.mapper.UserMapper;
import lift.miao.community.model.Question;
import lift.miao.community.model.User;
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
        Integer totalCount = questionMapper.count();
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
        List<Question> questionList = questionMapper.list(offset,pageSize);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        if(questionList!=null&&questionList.size()>0){
            for (Question question : questionList) {
                User user = userMapper.findById(question.getCreator());
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
        /*信息总条数*/
        Integer totalCount = questionMapper.countByUserId(userId);
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
        List<Question> questionList = questionMapper.listByUserId(userId,offset,pageSize);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        if(questionList!=null&&questionList.size()>0){
            for (Question question : questionList) {
                User user = userMapper.findById(question.getCreator());
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
     *获取单个问题详情
     * @param id
     * @return
     */
    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.getById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }


    public void createOrUpdate(Question question) {
        if(question.getId()==null){
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.create(question);
        }else {
            //更新
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.update(question);
        }
    }
}
