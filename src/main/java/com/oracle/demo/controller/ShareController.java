package com.oracle.demo.controller;


import com.oracle.demo.entity.Share;
import com.oracle.demo.entity.ShareInfo;
import com.oracle.demo.entity.SharePicture;
import com.oracle.demo.service.impl.ShareServiceImpl;
import com.oracle.demo.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class ShareController {
    @Autowired
    private ShareServiceImpl shareService;


    @RequestMapping("index")
    public String toIndex(Model model){
        List<ShareInfo> shareList=shareService.getAll();
        //List<SharePicture> sharePictureList = shareService.getAllPicture();
        model.addAttribute("shareList",shareList);
        //model.addAttribute("sharePictureList",sharePictureList);
        return "index";
    }

//    @RequestMapping("sendshare")
//    public String sendshare(@ModelAttribute Share share, MultipartFile file,HttpServletResponse response,
//                            SharePicture picture, HttpSession httpSession) throws IOException
//    {
//
//        shareService.sendShare(share,response);
//        System.out.println("用户id为："+share.getUserId()+"发布了一条动态,内容是："+share.getContent());
//        /**
//         * 上传图片
//         */
//        //图片上传成功后，将图片的地址写到数据库
//        String filePath = "D:/mysql_picture/";//保存图片的路径,tomcat中有配置
//        //获取原始图片的拓展名
//        String originalFilename = file.getOriginalFilename();
//        //新的文件名字，使用uuid随机生成数+原始图片名字，这样不会重复
//        String newFileName = UUID.randomUUID()+originalFilename;
//        //封装上传文件位置的全路径，就是硬盘路径+文件名
//        File targetFile = new File(newFileName);
//        //把本地文件上传到已经封装好的文件位置的全路径就是上面的targetFile
//        file.transferTo(targetFile);
//        picture.setImg(newFileName);//文件名保存到实体类对应属性上
//        picture.setShareId(share.getId());
//
//        /**
//         * 保存图片
//         */
//        //httpSession.setAttribute("share",share);
//        shareService.savePicture(picture);
//
//        return "redirect:index";//需要跳转至动态首页控制器
//    }

//    @Value("${web.upload-path}")
//    private String path;
//    /**
//     *
//     * @param file 要上传的文件
//     * @return
//     */
//    @RequestMapping("fileUpload")
//    public String upload(@ModelAttribute Share share, MultipartFile file,HttpServletResponse response,
//                         SharePicture picture, HttpSession httpSession) throws IOException{
//
//        /**
//         * 上传图片
//         */
//        //图片上传成功后，将图片的地址写到数据库
//        String filePath = "D:/mysql_picture/";//保存图片的路径,tomcat中有配置
//        //获取原始图片的拓展名
//        String originalFilename = file.getOriginalFilename();
//        //新的文件名字，使用uuid随机生成数+原始图片名字，这样不会重复
//        String newFileName = UUID.randomUUID()+originalFilename;
//        //封装上传文件位置的全路径，就是硬盘路径+文件名
//        File targetFile = new File(newFileName);
//        //把本地文件上传到已经封装好的文件位置的全路径就是上面的targetFile
//        file.transferTo(targetFile);
//        picture.setImg(newFileName);//文件名保存到实体类对应属性上
//        //picture.setShareId(share.getId());
//
//
//        shareService.savePicture(picture);
//
//        return "forward:/test";
//    }
//
//    private final ResourceLoader resourceLoader;
//   // @Value("${web.upload-path}")
//    //private String uploadPicturePath;
//
//    @Autowired
//    public ShareController(ResourceLoader resourceLoader) {
//        this.resourceLoader = resourceLoader;
//    }
//    @RequestMapping("test")
//    public String toeditor_test(Model model)
//    {
//        List<SharePicture> sharePictureList = shareService.getAllPicture();
//        model.addAttribute("sharePictureList",sharePictureList);
//        return "editor_test";
//    }
//    /**
//     * 显示单张图片
//     * @return
//     */
//    @RequestMapping("{fileName}")
//    public ResponseEntity showPhotos(String fileName){
//
//        try {
//            // 由于是读取本机的文件，file是一定要加上的， path是在application配置文件中的路径
//            return ResponseEntity.ok(resourceLoader.getResource("file:" + path + fileName));
//        } catch (Exception e) {
//            return ResponseEntity.notFound().build();
//        }
//    }

}