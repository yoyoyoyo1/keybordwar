package com.oracle.demo.controller;
/*
Created by szg
*/
import com.oracle.demo.entity.User;
import com.oracle.demo.service.MailService;
import com.oracle.demo.service.UserService;
import com.oracle.demo.util.MD5Util;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;


@Controller
public class MailController {
    @Autowired
    private MailService mailService;
    private HashMap<String,Object> codeMap = new HashMap<>();
    private HashMap<String,Object> recodeMap=new HashMap<>();
    //发送短信的url
    private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
    private HashMap<String,Object> mobileMap=new HashMap<>();
    @Autowired
    private UserService userService;
    @RequestMapping("user")
    public String userasd()
    {
        return "sign-in";
    }
    @RequestMapping("getcheckcode")
    @ResponseBody
    public String getchechcode(String email)
    {
        String checkcode=String.valueOf(new Random().nextInt(899999) + 100000);
        String msg="您的注册验证码为 +"+checkcode;
        try {
            mailService.sendCheckCodeMail(email,"注册验证码",msg);
            codeMap.put(email,checkcode);
        }catch (Exception e)
        {   //404页面
            return "404";
        }
        return checkcode;
    }
    //用户注册
    @RequestMapping("userreg")
    @ResponseBody
    public String userreg(String email,String ccode,String pass,String nickname,String phone)
    {
        User user=new User();
        user.setEmail(email);
        user.setNickname(nickname);
        user.setPhone(phone);
        user.setPass(MD5Util.encode(pass));
        //默认头像
        user.setImage("default.png");
        //默认签名
        user.setMotto("编辑自己的个性签名吧");
        if(ccode.equals(codeMap.get(email)))
        {
            try {
                if(userService.findByEmail(email)==null){
                System.out.println(email +"验证码验证正确");
                userService.addOne(user);
                System.out.println(email + "注册成功");
                return "ok";} else {
                    System.out.println(email +"该邮箱已经被注册");
                    return "bad";
                }
            }catch (Exception e){
                return "error";
            }

        }
        System.out.println(email+"验证错误");
        return "";
    }
    @RequestMapping("getrecode")
    @ResponseBody
    public String getrecode(String email,String phone)
    {
        if(userService.findByEandP(email,phone)==null)
        {
            return "bad";
        }else {
            HttpClient client = new HttpClient();
            PostMethod method = new PostMethod(Url);
            client.getParams().setContentCharset("UTF-8");
            method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=UTF-8");
            String  mobile_code = String.valueOf(new Random().nextInt(899999) + 100000);
            String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");
            mobileMap.put(phone,mobile_code);
            NameValuePair[] data = new NameValuePair[]{new NameValuePair("account", "C49830037"), new NameValuePair("password", "11b5eb237f4ead95b4ff3e05a4fc1cfd    "), new NameValuePair("mobile", phone), new NameValuePair("content", content)};
            method.setRequestBody(data);
            try {
                client.executeMethod(method);
                String SubmitResult = method.getResponseBodyAsString();
                Document doc = DocumentHelper.parseText(SubmitResult);
                Element root = doc.getRootElement();
                String code = root.elementText("code");
                String msg = root.elementText("msg");
                String smsid = root.elementText("smsid");
                System.out.println(code);
                System.out.println(msg);
                System.out.println(smsid);
                if (code == "2") {
                    System.out.println("短信提交成功");
                }
            } catch (HttpException var12) {
                var12.printStackTrace();
            } catch (IOException var13) {
                var13.printStackTrace();
            } catch (DocumentException var14) {
                var14.printStackTrace();
            }
            return "ok";
        }
    }
    @RequestMapping("userchangepwd")
    @ResponseBody
    public String userchangepwd(String email,String ccode,String pass,String phone)
    {
        System.out.println(mobileMap.get(phone)+"code-------------------");
        System.out.println(ccode+"code------------------");
        System.out.println(phone+"-----------------phone");
        if(ccode.equals(mobileMap.get(phone)))
        {
            userService.changePwd(MD5Util.encode(pass),email);
            return "ok";
        }else {
            return "bad";
        }
    }
}
