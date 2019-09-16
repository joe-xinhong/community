package lift.miao.community.cache;

import lift.miao.community.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.el.stream.Stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
* @Description:    缓存标签（省去数据库）
* @Author:         Joe
* @CreateDate:     2019/9/16 13:06
*/
public class TagCache {

    public static List<TagDTO> get(){
        List<TagDTO> tagDTOS= new ArrayList<>();
        TagDTO program = new TagDTO();
        program.setCategoryName("上路");
        program.setTags(Arrays.asList("上路","亚瑟","吕布","项羽","坦克","凯","猪八戒"));
        tagDTOS.add(program);

        TagDTO MidWay = new TagDTO();
        MidWay.setCategoryName("中路");
        MidWay.setTags(Arrays.asList("中路","法师","安琪拉","妲己","米莱迪","女娲","甄姬"));
        tagDTOS.add(MidWay);

        TagDTO wild = new TagDTO();
        wild.setCategoryName("打野");
        wild.setTags(Arrays.asList("打野","刺客","杨戬","宫本武藏","娜可露露","兰陵王","芈月"));
        tagDTOS.add(wild);

        TagDTO shooter = new TagDTO();
        shooter.setCategoryName("射手");
        shooter.setTags(Arrays.asList("射手","下路","鲁班","狄仁杰","孙尚香","马可波罗","虞姬"));
        tagDTOS.add(shooter);

        TagDTO subsidiary = new TagDTO();
        subsidiary.setCategoryName("辅助");
        subsidiary.setTags(Arrays.asList("辅助","庄周","牛魔","蔡文姬","大乔","明世隐","鬼谷子"));
        tagDTOS.add(subsidiary);

        return tagDTOS;
    }

    public static String filterInvalid(String tags){
        String[] split = StringUtils.split(tags, ",");
        List<TagDTO> tagDTOS = get();
        List<String> tagList = tagDTOS.stream().flatMap( tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));

        return invalid;
    }
}
//Arrays.asList("中路","法师","安琪拉","妲己","米莱迪","女娲","甄姬")
//Arrays.asList("打野","刺客","杨戬","宫本武藏","娜可露露","兰陵王","芈月")
//Arrays.asList("射手","下路","鲁班","狄仁杰","孙尚香","马可波罗","虞姬")
//Arrays.asList("辅助","庄周","牛魔","蔡文姬","大乔","明世隐","鬼谷子")