package com.example.demo;

import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.demo.utils.WebSocketUtils.*;

@RestController
@ServerEndpoint("/msg/")
public class WebSocketServer
{
    @OnOpen
    public void openSession(Session session)
    {
        // 获取当前连接的sessionID
        String sessionID = session.getId();

        // 保存当前连接
        ONLINE_USER_SESSIONS.put(sessionID, session);

        // 建立连接成功并发送通知
        sendMessage(session, "建立WebSocket连接成功！您的sessionID=" + sessionID);

        // 通知当前在线人数
        sendCurrentOnlineUsersSizeMsg();

        System.out.println("建立连接成功！sessionID=" + sessionID);
    }

    @OnMessage
    public void onMessage(Session session, String message)
    {
        System.out.println("接收到消息：" + message);
        if (message.equals("siri现在几点了"))
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH点mm分ss秒");
            Date now = new Date();
            String nowTime = sdf.format(now);

            // 给客户端发送消息
            sendMessage(session, "现在是" + nowTime);
        }
        else
        {
            // 给客户端发送消息
            sendMessage(session, "收到你发的消息了：" + message);
        }
    }

    @OnClose
    public void onClose(Session session)
    {
        // 当前的Session移除
        ONLINE_USER_SESSIONS.remove(session.getId());

        // 通知当前在线人数
        sendCurrentOnlineUsersSizeMsg();

        try
        {
            session.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println("已断开连接！");
    }

    @OnError
    public void onError(Throwable throwable)
    {
        System.out.println("报错了。错误信息：" + throwable.getMessage());
    }

    // 通知当前在线人数
    public void sendCurrentOnlineUsersSizeMsg()
    {
        sendMessageAll("当前在线人数：" + ONLINE_USER_SESSIONS.size());
    }
}