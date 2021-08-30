package com.example.rabbitdemo.mq.direct;

public interface IRabbitMQService {

    /**
     * 发送消息
     * @param msg 消息
     * @return 字符串
     * @throws Exception 异常
     */
    String sendMsg(String msg) throws Exception;
}
