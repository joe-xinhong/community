package lift.miao.community.interceptor;

import lift.miao.community.enums.AdPosEnum;
import lift.miao.community.mapper.UserMapper;
import lift.miao.community.model.User;
import lift.miao.community.model.UserExample;
import lift.miao.community.service.AdService;
import lift.miao.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
* @Description:    拦截器
* @Author:         Joe
* @CreateDate:     2019/9/10 21:27
*/
@Service
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private AdService adService;
    @Value("${gitee.redirect.uri}")
    private String redirectUri;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //设置 context 级别的属性
        request.getServletContext().setAttribute("redirectUri",redirectUri);
        //用户登录拦截
        Cookie[] cookies = request.getCookies();
        HttpSession session = request.getSession();
        //session.setAttribute("ads",adService.list());
        //遍历出枚举值进行分类查询集合
        for (AdPosEnum adPos:AdPosEnum.values()){
        request.getServletContext().setAttribute(adPos.name(),adService.list(adPos.name()));
        }
        /*request.getServletContext().setAttribute("ads",adService.list());*/
        if(cookies!=null && cookies.length>0)
            for (Cookie cookie:cookies){
                if(cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    UserExample userExample = new UserExample();
                    userExample.createCriteria().andTokenEqualTo(token);
                    List<User> users = userMapper.selectByExample(userExample);
                    if(users!=null && users.size()>0){
                        session.setAttribute("user",users.get(0));
                        Long unreadCount = notificationService.unreadCount(users.get(0).getId());
                        session.setAttribute("unreadCount",unreadCount);
                    }
                    break;
                }
            }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
