package com.oracle.demo.websocket;

import com.oracle.demo.entity.Message;
import com.oracle.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/dialog/{userId}/{dialogId}")
@Component
public class dialogWebSocket {
    @Autowired
    MessageService messageService;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    public static Map<String,Map<String,Session>>Dialog = new ConcurrentHashMap<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据

    //接收sid
    private String userId = "";
    private String dialogId = "";
    public Session session;
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,
                       @PathParam("userId") String userId,
                       @PathParam("dialogId") String dialogId) {
        Map<String,Session> userList =  Dialog.get(dialogId);

        if(userList==null || userList.size() >= 10){
//            onClose();
            System.out.println(1);
            return;
        }
        System.out.println(Dialog);
        userList.put(userId,session);
        this.dialogId = dialogId;
        this.session = session;
        this.userId = userId;
        System.out.println("userId"+userId);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        Dialog.get(dialogId).remove(userId);  //从set中删除
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        //群发消息
        Message mess =  new Message();
        mess.setFormId(Integer.parseInt(userId));
        mess.setContent(message);
        mess.setCreateAt(new Date());
        mess.setWatch(2);
        mess.setToId(Integer.parseInt(dialogId) );
        System.out.println(mess.toString());
//        messageService.save(mess);
        System.out.println(message);
        Map<String, Session> dialog = Dialog.get(dialogId);
        for (String key : dialog.keySet()) {
            dialog.get(key).getBasicRemote().sendText(mess.toString());;
        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        Dialog.get(dialogId).remove(userId);  //从set中删除
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
