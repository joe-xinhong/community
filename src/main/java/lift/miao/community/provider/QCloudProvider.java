package lift.miao.community.provider;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.model.COSObject;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import lift.miao.community.exception.CustomizeErrorCode;
import lift.miao.community.exception.CustomizeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

/**
* @Description:    腾讯云存储
* @Author:         Joe
* @CreateDate:     2019/9/17 14:29
*/
@Service
public class QCloudProvider {

    @Value("${qcloud.ufile.SecretId}")
    private  String secretId;
    @Value("${qcloud.ufile.SecretKey}")
    private  String secretKey;
    @Value("${qcloud.ufile.bucket}")
    private  String bucket;
    @Value("${qcloud.ufile.region}")
    private  String region;

    /**
     * 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20 M 以下的文件使用该接口
     * 大文件上传请参照 API 文档高级 API 上传
     * @param localFile
     */
    public String uploadfile(File localFile,String defaultKey)throws CosClientException{
        String generatedFileName;
        String[] filePaths = localFile.getName().split("\\.");
        if(filePaths.length>1){
            generatedFileName = UUID.randomUUID().toString()+"."+filePaths[filePaths.length-1];
        }else {
            throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAIL);
        }
        // 1 初始化用户身份信息(secretId, secretKey，可在腾讯云后台中的API密钥管理中查看！
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224，根据自己创建的存储桶选择地区
        ClientConfig clientConfig = new ClientConfig(new Region(region));
        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式，这个为存储桶名称
        String bucketName = bucket;
        // 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);
        try {
            // 指定要上传到 COS 上的路径
            String key = defaultKey+generatedFileName;
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
            PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
            if(putObjectRequest!=null){
                Date expiration = new Date(new Date().getTime() + 3600 * 1000 * 24 * 365 * 10);
                URL oldurl = cosclient.generatePresignedUrl(bucketName, key, expiration);
                String url = oldurl.toString();
                //String url11 = url.substring(0, url.indexOf("?"));
                return url;
            }else {
                throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAIL);
            }

        }catch (Exception e){
            e.printStackTrace();
            throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAIL);
        }finally {
            // 关闭客户端(关闭后台线程)
            cosclient.shutdown();
        }

    }

}
