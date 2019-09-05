package lift.miao.community.controller;

import lift.miao.community.dto.AccessTokenDTO;
import lift.miao.community.dto.GitUser;
import lift.miao.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @RequestMapping("/callback")
    public String callback(@RequestParam(name = "code")String code){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("0612af6cad44d6f2e90134725436fe18def380fce1c6c717117ce0ca5b85eec3");
        accessTokenDTO.setClient_secret("1a9b4afd4085922b10534fca1461e6ab4bfd89def501f788ca7b3689b65cc563");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8088/callback");
        accessTokenDTO.setGrant_type("authorization_code");
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GitUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
