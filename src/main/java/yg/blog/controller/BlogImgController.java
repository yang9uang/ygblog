package yg.blog.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import yg.blog.pojo.BlogImg;
import yg.blog.serivce.BlogImgService;
import yg.blog.utils.ImageUtils;
import yg.blog.utils.QiniuUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/img")
public class BlogImgController {

    @Autowired
    BlogImgService blogImgService;


    /**
     * 加载主页图片
     * @return
     */
    @ResponseBody
    @RequestMapping("/indexImg")
    public Map<String,Object> indexImg(){
        List<BlogImg> blogImgs = blogImgService.queryImgindex("1");
        Map<String,Object> map = new HashMap<String, Object>();
        if (blogImgs!=null && blogImgs.size()>0){
            map.put("status",200);
            map.put("data",blogImgs);
            return map;
        }
        return (Map<String, Object>) map.put("status",500);
    }


    @ResponseBody
    @RequestMapping(value = "/system",method = RequestMethod.GET)
    public Map<String,Object> systems(){
        List<BlogImg> result = blogImgService.queryAll();
        Map<String,Object> map = new HashMap<String, Object>();
        if (result.size()!=0 && result.size()>0) {
            map.put("status",200);
            map.put("data",result);
            return map;
        }
        return (Map<String, Object>) map.put("status",500);
    }

    @ResponseBody
    @RequestMapping(value = "/editImg",method = RequestMethod.POST)
    public Map<String,Object> editImg(@RequestParam("imgid")String id){
        System.err.println(id);
        List<BlogImg> list = blogImgService.editimg(id);
        Map<String,Object> map = new HashMap<String, Object>();
        if (list.size()!=0&&list.size()>0){
            map.put("status",200);
            map.put("data",list);
            return map;
        }
        return (Map<String, Object>) map.put("status",500);
    }

    @ResponseBody
    @RequestMapping(value = "/delImg",method = RequestMethod.POST)
    public Map<String,Object> del(@RequestParam("imgid")String id){
        System.err.println(id);
        int count = blogImgService.del(id);
        Map<String,Object> map = new HashMap<String, Object>();
        if (count == 1){
            map.put("status",200);
            return map;
        }
        return (Map<String, Object>) map.put("status",500);
    }

    @ResponseBody
    @RequestMapping(value = "updateEdit",method = RequestMethod.POST)
    public Map<String, Object> updateEdit(@RequestBody BlogImg blogImg){
        Integer count = blogImgService.updateEdit(blogImg);
        Map<String,Object> map = new HashMap<String, Object>();
        if(count == 1){
            map.put("status",200);
            return map;
        }
        return (Map<String, Object>) map.put("status",500);
    }

    @ResponseBody
    @RequestMapping(value = "/uploadImg",method = RequestMethod.POST)
    public JSONObject uploadImg(@RequestParam(value = "files")MultipartFile file,@RequestParam(value = "imgtext")String imgtext) throws IOException {
        QiniuUtils qiniuUtils = new QiniuUtils();
        String upload = qiniuUtils.upload(file);
        JSONObject jsonObject = new JSONObject();
        if (upload!=null){
            Integer upload1 = blogImgService.upload(upload, imgtext);
            if (upload1 <= 0 ){
                return (JSONObject) jsonObject.put("status",200);
            }
        }
        return (JSONObject) jsonObject.put("status",500);
    }

}
