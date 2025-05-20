package org.my.springstart.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.util.UUID;

@Slf4j
public class OssUtil {
    /**
     * 上传文件
     * @param endpoint endpoint域名
     * @param bucketName 存储空间的名字
     * @param content 内容字节数组
     */
    public static String upload(String endpoint, String bucketName, String accessKeyId, String accessKeySecret,byte[] content, String extName) throws Exception {
        // 使用代码嵌入的STS临时访问密钥和安全令牌配置访问凭证。
        CredentialsProvider credentialsProvider = new DefaultCredentialProvider(accessKeyId, accessKeySecret);


        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
        String objectName = UUID.randomUUID() + extName;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);
        try {
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new ByteArrayInputStream(content));
            // 创建PutObject请求。
            PutObjectResult result = ossClient.putObject(putObjectRequest);
            log.info("{}", result);
        } catch (OSSException oe) {
            log.error("Caught an OSSException, which means your request made it to OSS, but was rejected with an error response for some reason.");
            log.error("Error Message:" + oe.getErrorMessage());
            log.error("Error Code:" + oe.getErrorCode());
            log.error("Request ID:" + oe.getRequestId());
            log.error("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            log.error("Caught an ClientException, which means the client encountered a serious internal problem while trying to communicate with OSS, such as not being able to access the network.");
            log.error("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }


        System.out.println(endpoint + "000000");
        return "https://" + bucketName + "." + endpoint + "/" + objectName;
    }
}
