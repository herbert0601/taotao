package com.taotao.controller;

import com.taotao.common.utils.JsonUtils;
import com.taotao.utils.FastDFSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

/**
 * @author Herbert
 * @create 2019-06-15 18:03
 */
@Controller
public class PictureController {

    @Value("${IMAGE_SERVER_URL}")//@value属性注入，Springmvc.xml配置扫描properties文件信息
    private String IMAGE_SERVER_URL;

    @RequestMapping(value="/pic/upload",produces = MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
    @ResponseBody
    public String fileUpload(MultipartFile uploadFile) {
        //1.取文件的扩展名
        String originalFilename = uploadFile.getOriginalFilename();
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        //2。创建一个fastdfs的客户端
        try {
            FastDFSClient fastDFSClient = fastDFSClient = new FastDFSClient("classpath:resource/fast_dfs.conf");
            //3。执行上传处理
            String path = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
            //4。拼接返回的URL和IP地址，瓶装完整的URL
            String url = IMAGE_SERVER_URL + path;
            //5。返回map
            HashMap<Object, Object> result = new HashMap<>();
            result.put("error", 0);
            result.put("url", url);
            String json = JsonUtils.objectToJson(result);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            //异常
            HashMap<Object, Object> result = new HashMap<>();
            result.put("error", 1);
            result.put("message", "图片上传失败");
            String json = JsonUtils.objectToJson(result);
            return json;
        }

    }
}
