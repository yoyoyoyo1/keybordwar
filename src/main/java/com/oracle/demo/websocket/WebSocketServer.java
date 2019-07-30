package com.oracle.demo.websocket;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.oracle.demo.bean.Message;
import com.oracle.demo.entity.Message;
import com.oracle.demo.util.BeanToMap;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import static com.oracle.demo.websocket.dialogWebSocket.messageService;


@ServerEndpoint("/websocket/{from}")
@Component
public class WebSocketServer {

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static Map<Integer,Session> sessionMap = new ConcurrentHashMap<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //接收sid
    private Integer from ;
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,
                       @PathParam("from") Integer from) {
        this.session = session;
        this.from=from;
        sessionMap.put(from,session);

    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        sessionMap.remove(from);  //从set中删除
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        JSONObject messageMap = JSONObject.fromObject(message);
        Integer toId = (Integer) messageMap.get("toId");
        System.out.println(messageMap);
        Message mess = new Message();
        mess.setCreateAt(new Date());
        mess.setToId(toId);
        mess.setFormId(from);
        mess.setContent((String) messageMap.get("content"));
        mess.setType((String) messageMap.get("type"));
        mess.setWatch(0);
        Map<String, Object> a = BeanToMap.BeanToMap(mess);
        a.put("type",JSON.toJSONString(mess));
        if(sessionMap.get(toId) != null){
            sendMessage(toId,JSON.toJSONString(mess));
        }
        sendMessage(from,JSON.toJSONString(mess));
        messageService.save(mess);
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        sessionMap.remove(from);
        error.printStackTrace();
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(Integer toId,Object message) throws IOException{
        sessionMap.get(toId).getBasicRemote().sendText(message.toString());

    }


    /**
     * 群发自定义消息
     * */
    public static void sendInfo(String message,@PathParam("sid") String sid) throws IOException {
    }


}
