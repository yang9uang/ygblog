package yg.blog.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yg.blog.pojo.BlogUser;
import yg.blog.serivce.BlogUserService;
import yg.blog.utils.ImageUtils;

import javax.jws.WebMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("bloguser")
public class BloguserController {

    @Autowired
    BlogUserService blogUserService;

    @ResponseBody
    @RequestMapping("/select")
    public List<BlogUser> selectAll(){
        List<BlogUser> blogUsers = blogUserService.selectAll();
        return blogUsers;
    }

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@Param("username") String username,@Param("password") String password){
        System.err.println(username+"---"+password);
        BlogUser blogUser = blogUserService.login(username,password);
        if (blogUser!=null){
            return "success";
        }else {
            return "error";
        }
    }

    @ResponseBody
    @RequestMapping("/uploadImg")
    public void uploadImg(HttpServletRequest request) throws UnsupportedEncodingException {

        request.setCharacterEncoding("UTF-8");
        //获取文件的路径
//        String path = session.getServletContext().getRealPath("/")+"webapp/static/img/img01.jpg";
//        String url = session.getServletContext().getRealPath("/") + "resources / images / act / worldcup_merge / worldcup720.png” ;
//        String path = request.getSession().getServletContext().getContextPath();
//        String path2 = session.getServletContext().getRealPath("/")+"webapp/static/imgChange/img001.jpg";
        String path = request.getSession().getServletContext().getRealPath("/")+"static/img/img05-index.jpg";
        String path2 = request.getSession().getServletContext().getRealPath("/")+"static/imgChange/img05-indexscale2.jpg";
        System.out.println("path: " + path);
        ImageUtils.scale2(path,path2,1080,1920,true);
        System.out.println("path: " + path);
//        String path2 = path + "static/img/img01scale.jpg";
//        ImageUtils.scale(path,path2,2,true);
    }

}
