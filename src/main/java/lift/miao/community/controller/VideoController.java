package lift.miao.community.controller;

import io.swagger.annotations.Api;
import lift.miao.community.cache.HotTagCache;
import lift.miao.community.dto.PaginationDTO;
import lift.miao.community.mapper.UserMapper;
import lift.miao.community.model.User;
import lift.miao.community.model.UserExample;
import lift.miao.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
* @Description:    视频数据
* @Author:         Joe
* @CreateDate:     2019/9/4 14:40
*/
@Controller
@Api(value = "视频数据接口",description = "视频数据接口")
public class VideoController {

    @RequestMapping("/videos")
    public String index(){
        return "videos";
    }

    @RequestMapping(value = "/video",method = RequestMethod.GET)
    public String video(@RequestParam(name = "aid")String aid,
                        @RequestParam(name = "page",defaultValue = "1")String page,
                        Model model){
        model.addAttribute("aid",aid);
        model.addAttribute("page",page);
        return "video";
    }
}
