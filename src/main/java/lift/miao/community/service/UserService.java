package lift.miao.community.service;

import lift.miao.community.mapper.UserMapper;
import lift.miao.community.model.User;
import lift.miao.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> dbUsers = userMapper.selectByExample(userExample);
        if (dbUsers==null || dbUsers.size()==0){
            //插入数据
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else {
            User dbUser = dbUsers.get(0);
            //更新
            User updateUser = new User();
            updateUser.setGmtModified(System.currentTimeMillis());
            updateUser.setToken(user.getToken());
            updateUser.setName(user.getName());
            updateUser.setAvatarUrl(user.getAvatarUrl());
            UserExample userExample1 = new UserExample();
            userExample1.createCriteria().andIdEqualTo(dbUser.getId());
            userMapper.updateByExampleSelective(updateUser,userExample1);
        }
    }
}
