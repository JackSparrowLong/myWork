package com.example.rabbitdemo.controller;

import com.example.rabbitdemo.mq.direct.IRabbitMQService;
import com.example.rabbitdemo.mq.fanout.IRabbitMqFanoutService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/rabbitmq")
public class RabbitMQController {
    @Resource
    private IRabbitMQService rabbitMQService;

    @Resource
    private IRabbitMqFanoutService rabbitMqFanoutService;

    @PostMapping("/sendMsg")
    public String sendMsg(@RequestParam(name="msg")String msg) throws Exception{
        return rabbitMQService.sendMsg(msg);
    }
    @PostMapping("/sendFanoutMsg")
    public String sendFanoutMsg(@RequestParam(name = "msg")String msg)throws Exception{
        return rabbitMqFanoutService.sendMsgByFanoutExchange(msg);
    }

}
