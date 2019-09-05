package lift.miao.community.controller;

import lift.miao.community.dto.AccessTokenDTO;
import lift.miao.community.dto.GitUser;
import lift.miao.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
* @Description:    授权登录
* @Author:         Joe
* @CreateDate:     2019/9/5 15:59
*/
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;
    @Value("${gitee.client.id}")
    private String clientId;
    @Value("${gitee.client.secret}")
    private String clientSecret;
    @Value("${gitee.redirect.uri}")
    private String redirectUri;
    
    @RequestMapping("/callback")
    public String callback(@RequestParam(name = "code")String code){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setGrant_type("authorization_code");
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GitUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
