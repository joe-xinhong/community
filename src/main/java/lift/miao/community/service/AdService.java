package lift.miao.community.service;

import lift.miao.community.mapper.AdMapper;
import lift.miao.community.model.Ad;
import lift.miao.community.model.AdExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description:    广告业务
* @Author:         Joe
* @CreateDate:     2019/10/12 17:14
*/
@Service
public class AdService {
    @Autowired
    private AdMapper adMapper;

    public List<Ad> list(String posName){
        AdExample adExample = new AdExample();
        adExample.createCriteria()
                .andStatusEqualTo(1)
                .andPosEqualTo(posName)
        .andGmtStartLessThan(System.currentTimeMillis())
        .andGmtEndGreaterThan(System.currentTimeMillis());
        List<Ad> adList = adMapper.selectByExample(adExample);
        return adList;
    }

}
