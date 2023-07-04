package com.cf.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Component
public class AliOSSUtils {
/*    // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
    String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
    // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
    String accessKeyId = "LTAI5tJ3Qz8BwHPGC7iMTnc4";
    String accessKeySecret = "Pe9UCCwdxuSLQs7cHMDzStdonSrd7P";
    // 填写Bucket名称，例如examplebucket。
    String bucketName = "web-tlias123";*/

/*    //利用配置文件里的值
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.accessKeyId}")
    private  String accessKeyId;
    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliyun.oss.bucketName}")
    private String bucketName   ;*/

    @Autowired
    private AliOSSProperties aliOSSProperties;

    public String upload(MultipartFile file) throws IOException {

        String endpoint = aliOSSProperties.getEndpoint();
        String accessKeyId = aliOSSProperties.getAccessKeyId();
        String accessKeySecret = aliOSSProperties.getAccessKeySecret();
        String bucketName  = aliOSSProperties.getBucketName();

        InputStream inputStream = file.getInputStream();

        //使用UUID避免文件重复
        String originalFilename = file.getOriginalFilename();
        String Filename = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //上传文件到OSS
        OSS ossClient =new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);
        ossClient.putObject(bucketName,Filename,inputStream);

        String url = endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + Filename;
        ossClient.shutdown();
        return url;
    }

}