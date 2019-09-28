package lift.miao.community.cache;
import	java.util.Collections;
import	java.util.ArrayList;
import java.util.List;
import	java.util.PriorityQueue;

import lift.miao.community.dto.HotTagDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import	java.util.Map;
/**
* @Description:    缓存热点标题
* @Author:         Joe
* @CreateDate:     2019/9/28 20:58
*/
@Component
@Data
public class HotTagCache {
    private List<String> hots = new ArrayList<> ();

    public void updateTages(Map<String, Integer> tags) {
        Integer max = 10;
        PriorityQueue<HotTagDTO> priorityQueue = new PriorityQueue<> (max);

        tags.forEach((name,priority)->{
            HotTagDTO hotTagDTO = new HotTagDTO();
            hotTagDTO.setName(name);
            hotTagDTO.setPriority(priority);
            if(priorityQueue.size()<max){
                priorityQueue.add(hotTagDTO);
            }else {
                HotTagDTO minHot = priorityQueue.peek();
                if(hotTagDTO.compareTo(minHot)>0){
                    priorityQueue.poll();
                    priorityQueue.add(hotTagDTO);
                }
            }
        });
        List<String> sortedList = new ArrayList<>();

        HotTagDTO poll = priorityQueue.poll();
        while (poll != null) {
            sortedList.add(0,poll.getName());
            poll = priorityQueue.poll();
        }
        hots = sortedList;
        System.out.println(hots);

    }
}
