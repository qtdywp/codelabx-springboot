package com.example.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class WebSocketUtils
{
    private static final Logger logger = LoggerFactory.getLogger(WebSocketUtils.class);

    // 存储WebSocket连接的Session实例
    public static final Map<String, Session> ONLINE_USER_SESSIONS = new ConcurrentHashMap<>();

    /**
     * @param session 当前WebSocket连接的Session实例
     * @param message 消息内容
     */
    public static void sendMessage(Session session, String message)
    {
        if (session == null)
        {
            return;
        }
        final RemoteEndpoint.Basic basic = session.getBasicRemote();
        if (basic == null)
        {
            return;
        }
        try
        {
            basic.sendText(message);
        }
        catch (IOException e)
        {
            logger.error("sendMessage IOException ", e);
        }
    }

    // 给当前所有的WebSocket连接发送消息
    public static void sendMessageAll(String message)
    {
        ONLINE_USER_SESSIONS.forEach((sessionId, session) -> sendMessage(session, message));
    }

}