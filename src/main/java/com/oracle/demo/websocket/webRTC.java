package com.oracle.demo.websocket;

import com.oracle.demo.bean.Message;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/webRTC/{from}/{to}")
@Component
public class webRTC {
    //concurrent包的线程安全Map，用来存放每个客户端对应的MyWebSocket对象。
    private static Map<String,Session> webSocketMap =  new ConcurrentHashMap<>();

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
        webSocketMap.put(from,session);
        this.from=from;
        this.to = to;
        System.out.println(from);
        System.out.println(webSocketMap);
    }

    @OnClose
    public void onClose() {
        webSocketMap.remove(from);  //从set中删除
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println(message);
        webSocketMap.get(to).getBasicRemote().sendText(message);
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
