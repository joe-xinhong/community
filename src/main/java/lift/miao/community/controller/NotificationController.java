package lift.miao.community.controller;

import lift.miao.community.dto.NotificationDTO;
import lift.miao.community.enums.NotificationTypeEnum;
import lift.miao.community.model.User;
import lift.miao.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
* @Description:    描述
* @Author:         Joe
* @CreateDate:     2019/9/16 20:41
*/
@Controller
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @RequestMapping("/notification/{id}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "id")Long id){
        User user = (User)request.getSession().getAttribute("user");
        if(user==null){
            return "redirect:/";
        }

        NotificationDTO notificationDTO =  notificationService.read(id,user);
        if(NotificationTypeEnum.REPLY_COMMENT.getType()==notificationDTO.getType()
                || NotificationTypeEnum.REPLY_QUESTION.getType()== notificationDTO.getType()){
            return "redirect:/question/"+notificationDTO.getOuterId();
        }else {
            return "redirect:/";
        }

    }
}
