package com.oracle.demo.controller;


import com.oracle.demo.entity.Share;
import com.oracle.demo.entity.ShareInfo;
import com.oracle.demo.entity.SharePicture;
import com.oracle.demo.respository.SharePictureDao;
import com.oracle.demo.service.impl.ShareServiceImpl;
import com.oracle.demo.util.MD5Util;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class ShareController {
    @Autowired
    private ShareServiceImpl shareService;
    @Autowired
    private SharePictureDao sharePictureDao;


    @RequestMapping("index")
    public String toIndex(Model model){
        List<ShareInfo> shareList=shareService.getAll();
        //List<SharePicture> sharePictureList = shareService.getAllPicture();
        model.addAttribute("shareList",shareList);
        List<SharePicture> simg=sharePictureDao.findimg();
        model.addAttribute("shareplist",simg);
        //model.addAttribute("sharePictureList",sharePictureList);
        return "index";
    }

    @RequestMapping("sendshare")
    public String sendshare(@ModelAttribute Share share,@RequestParam("shareimg") MultipartFile[] file,HttpServletResponse response,
                            SharePicture picture) throws IOException
    {

        shareService.sendShare(share,response);
        System.out.println("用户id为："+share.getUserId()+"发布了一条动态,内容是："+share.getContent());
        /**
         * 上传图片
         */
        String x =file[0].getOriginalFilename();
        List<String > list=new ArrayList<>();
        if(!file[0].getOriginalFilename().isEmpty()){
            for(int i=0;i<file.length;i++){
                System.out.println("文件路径"+i+":"+file[i].getOriginalFilename());
                System.out.println("文件的input："+i+":"+file[i].getInputStream());
                String fileName1=file[i].getOriginalFilename();
                Thumbnails.of(file[i].getInputStream()).scale(0.4).toFile(fileName1);
                String fileName= MD5Util.encode(share.getId()+"")+fileName1;
                System.out.println("加密路径："+fileName);
                //相对地址
                String filePath= ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/images/";
                //Thumbnails.of(fileName).scale(0.4).toFile(fileName);
                File dest=new File(filePath + fileName);
               // try{
                    Thumbnails.of(file[i].getInputStream()).scale(0.5f).outputQuality(0.25f).toFile(dest);
            //    }catch (IOException e){
                    file[i].transferTo(dest);
                    //picture.setImg(fileName);//文件名保存到实体类对应属性上
                    //picture.setShareId(share.getId());

        //       }
               //catch (IOException e1){
//                    e1.printStackTrace();
//                }
                System.out.println("上传图片："+fileName+"4klasfjd"+share.getId());
                shareService.savePicture(fileName,share.getId());
            }
        }



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

        /**
         * 保存图片
         */
        //httpSession.setAttribute("share",share);


        return "redirect:index";//需要跳转至动态首页控制器
    }



 //   private final ResourceLoader resourceLoader;
   // @Value("${web.upload-path}")
    //private String uploadPicturePath;

//    @Autowired
//    public ShareController(ResourceLoader resourceLoader) {
//        this.resourceLoader = resourceLoader;
//    }
    @RequestMapping("test")
    public String toeditor_test(Model model)
    {
        List<SharePicture> sharePictureList = shareService.getAllPicture();
        model.addAttribute("sharePictureList",sharePictureList);
        return "editor_test";
    }
//    /**
//     * 显示单张图片
//     * @return
//     */
//    @RequestMapping("/show")
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