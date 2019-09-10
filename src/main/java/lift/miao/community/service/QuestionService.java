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
        /*信息总条数*/
        Integer totalCount = questionMapper.count();
        paginationDTO.setPagination(totalCount,page,pageSize);
        if(page<1){
            page=1;
        }
        if(page>paginationDTO.getTotalPage()){
            page=paginationDTO.getTotalPage();
        }
        Integer offset = pageSize * (page-1);
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
}
