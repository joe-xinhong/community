package lift.miao.community.controller;

import lift.miao.community.dto.FileDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
* @Description:    文件上传
* @Author:         Joe
* @CreateDate:     2019/9/17 11:45
*/
@Controller
public class FileController {

    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDTO upload(){
        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setUrl("/images/a.jpg");
        return fileDTO;
    }
}
