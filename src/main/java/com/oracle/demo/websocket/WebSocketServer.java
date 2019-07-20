package com.oracle.demo.websocket;

import com.oracle.demo.entity.Message;
import com.oracle.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint("/websocket/{from}/{to}")
@Component
public class WebSocketServer {

    @Autowired
    MessageService messageService;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static Map<String,Map<String,Session>> sessionMap = new ConcurrentHashMap<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;


   /* public static Map<String, Session> getSessionMap() {
        return sessionMap;
    }*/

    //接收sid
    private String from = "";
    private String to = "";
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,
                       @PathParam("from") String from,
                       @PathParam("to") String to) {
        Map<String,Session> chatlist=sessionMap.get(to);
        System.out.println(sessionMap);
        this.session = session;
        this.from=from;
        this.to = to;
        chatlist.put(from,session);
        //WebSocketServer.onlineCount++;
        //sendOnlineCount(session,"{'type':'onlineCount','onlineCount':\" + WebSocketServer.onlineCount + \",to:'\" + to + \"'}")

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
        //发消息
        Message mess =  new Message();
        mess.setFormId(Integer.parseInt(from));
        mess.setContent(message);
        mess.setCreateAt(new Date());
        mess.setWatch(1);
        mess.setToId(Integer.parseInt(to) );
        System.out.println(mess.toString());
        Map<String,Session> chatlist=sessionMap.get(to);
        /*if(sessionMap.get(to)!=null){
            sessionMap.get(to).sendMessage(mess);
        }
        sessionMap.get(from).sendMessage(mess);*/
        //for (String key:sessionMap.keySet()){
           // sessionMap.get(key).getBasicRemote().sendText(mess.toString());
        //}

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
    public void sendMessage(Object message) throws IOException{
        this.session.getBasicRemote().sendText(message.toString());
    }


    /**
     * 群发自定义消息
     * */
    public static void sendInfo(String message,@PathParam("sid") String sid) throws IOException {
    }

}
