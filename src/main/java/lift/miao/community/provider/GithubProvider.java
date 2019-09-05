package lift.miao.community.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lift.miao.community.dto.AccessTokenDTO;
import lift.miao.community.dto.GitUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
* @Description:    Github支持（第三方提供者）
* @Author:         Joe
* @CreateDate:     2019/9/5 16:17
*/
@Component//使用该注解初始化Spring上下文，不需要new。不需要实例化
public class GithubProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://gitee.com/oauth/token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string =response.body().string();
            JSONObject jsonObject = JSONObject.parseObject(string);
            String access_token = jsonObject.get("access_token").toString();
            return access_token;
        } catch (IOException e) {
        }
        return null;
    }

    public GitUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://gitee.com/api/v5/user?access_token="+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string =response.body().string();
            System.out.println("get:"+string);
            GitUser gitUser = JSON.parseObject(string, GitUser.class);
            return gitUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
