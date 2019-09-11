package lift.miao.community.service;

import lift.miao.community.mapper.UserMapper;
import lift.miao.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @Description:    用户业务处理
* @Author:         Joe
* @CreateDate:     2019/9/11 17:43
*/
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 创建或者修改用户信息
     * @param user
     */
    public void createOrUpdate(User user) {
        User dbUser = userMapper.findByAccountId(user.getAccountId());
        if (dbUser==null){
            //插入数据
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else {
            dbUser.setGmtModified(System.currentTimeMillis());
            dbUser.setToken(user.getToken());
            dbUser.setName(user.getName());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            //更新
            userMapper.update(dbUser);
        }
    }
}
