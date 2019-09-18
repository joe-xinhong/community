package lift.miao.community.controller;

import lift.miao.community.dto.FileDTO;
import lift.miao.community.dto.FileUtils;
import lift.miao.community.provider.QCloudProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
* @Description:    文件上传
* @Author:         Joe
* @CreateDate:     2019/9/17 11:45
*/
@Controller
public class FileController {
    @Autowired
    private QCloudProvider qcloudProvider;

    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDTO upload(HttpServletRequest request){
        String defaultKey = "upload/";
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        MultipartFile file = multipartRequest.getFile("editormd-image-file");
        File newFile = FileUtils.multipartFileToFile(file);
        try {
            String fileName = qcloudProvider.uploadfile(newFile,defaultKey);
            System.out.println("返回的文件："+fileName);
            FileDTO fileDTO = new FileDTO();
            fileDTO.setSuccess(1);
            fileDTO.setUrl(fileName);
            return fileDTO;
        }catch (Exception e){
            e.printStackTrace();
        }
        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setUrl("/images/a.jpg");
        return fileDTO;
    }
}
