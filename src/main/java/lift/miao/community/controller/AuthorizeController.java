package lift.miao.community.controller;

import io.swagger.annotations.Api;
import lift.miao.community.dto.AccessTokenDTO;
import lift.miao.community.dto.GitUser;
import lift.miao.community.model.User;
import lift.miao.community.provider.GithubProvider;
import lift.miao.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
* @Description:    授权登录
* @Author:         Joe
* @CreateDate:     2019/9/5 15:59
*/
@Controller
@Api(value = "授权接口",description = "授权接口")
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;
    @Value("${gitee.client.id}")
    private String clientId;
    @Value("${gitee.client.secret}")
    private String clientSecret;
    @Value("${gitee.redirect.uri}")
    private String redirectUri;
    @Autowired
    private UserService userService;
    
    @RequestMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,
                           HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setGrant_type("authorization_code");
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GitUser gitUser = githubProvider.getUser(accessToken);
        if(gitUser!=null&&gitUser.getId()!=null){
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(gitUser.getName());
            user.setAccountId(String.valueOf(gitUser.getId()));
            user.setAvatarUrl(gitUser.getAvatar_url());
            userService.createOrUpdate(user);
            //登录成功，写cookie和Session
            Cookie cookie = new Cookie("token",token);
            response.addCookie(cookie);
            //重定向到首页
            return "redirect:/";
        }else {
            //登录失败，重新登录
            return "redirect:/";
        }
    }

    /**
     * 退出登录
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
