package com.oracle.demo.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.demo.bean.Message;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint("/websocket/{from}/{to}")
@Component
public class WebSocketServer {

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    public static Map<String,WebSocketServer> webSocketMap = new ConcurrentHashMap<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //接收sid
    private String from = "";
    private String to = "";
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,
                       @PathParam("from") String from,
                       @PathParam("to") String to) {
        this.session = session;
        webSocketMap.put(from,this);
        this.from=from;
        this.to = to;
        Message message =  new Message();
        message.setUserId(from);
        message.setMessage("hello");
        try {
            sendMessage(message);
        } catch (IOException e) {
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketMap.remove(from);  //从set中删除
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        //群发消息
        Message mess =  new Message();
        mess.setUserId(from);
        mess.setMessage(message);
        if(webSocketMap.get(to)!=null){
            webSocketMap.get(to).sendMessage(mess);
        }
        webSocketMap.get(from).sendMessage(mess);

        /*try {//私聊消息
            HashMap hashMap = new ObjectMapper().readValue(message, HashMap.class);

            //消息类型
            String type = (String) hashMap.get("type");

            //来源用户
            Map srcUser = (Map) hashMap.get("from");

            //目标用户
            Map tarUser = (Map) hashMap.get("to");

            //如果点击的是自己，那就是群聊
            if (srcUser.get("nickname").equals(tarUser.get("nickname"))) {
                //群聊
                groupChat(session, hashMap);
            } else {
                //私聊
                privateChat(session, tarUser, hashMap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        webSocketMap.remove(from);
        error.printStackTrace();
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(Object message) throws IOException{
        this.session.getBasicRemote().sendText(message.toString());
    }


    /**
     * 群发自定义消息
     * */
    public static void sendInfo(String message,@PathParam("sid") String sid) throws IOException {
    }


}
