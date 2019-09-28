package lift.miao.community.schedule;
import	java.util.HashMap;
import java.util.ArrayList;
import	java.util.Date;
import java.util.List;
import java.util.Map;

import lift.miao.community.cache.HotTagCache;
import lift.miao.community.mapper.QuestionMapper;
import lift.miao.community.model.Question;
import lift.miao.community.model.QuestionExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
* @Description:    定时任务类
* @Author:         Joe
* @CreateDate:     2019/9/28 20:07
*/
@Component
@Slf4j
public class HotTagTasks {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private HotTagCache hotTagCache;

    @Scheduled(fixedRate = 1000 * 60 * 60 * 3) /*每隔3小时执行一次*/
    //@Scheduled(cron = "0 0 1 * * *")
    public void reportCurrentTime(){
        Integer offset = 0;
        Integer limit = 10;
        log.info("HotTagTasks  start {}",new Date());
        List<Question> list = new ArrayList<>();
        Map<String,Integer> tagMap = new HashMap<> ();
        while (offset ==0 || list.size() ==limit) {
            list = questionMapper.selectByExampleWithRowbounds(new QuestionExample(),new RowBounds(offset,limit));
            for (Question question : list) {
                String[] tags = StringUtils.split(question.getTag(), ",");
                for (String tag : tags) {
                    Integer priority = tagMap.get(tag);
                    if(priority!=null){
                        tagMap.put(tag, priority +5 + question.getCommentCount());
                    }else {
                        tagMap.put(tag,5 + question.getCommentCount());
                    }
                }
            }
            offset += limit;
        }
        hotTagCache.updateTages(tagMap);
        log.info("HotTagTasks  stop {}",new Date());

    }
}
