package com.xp.service;

import com.xp.entity.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @Author xp
 * @CreateTime 2019/03/12  23:52
 * @Function 用来监听来自于消息队列里book相关的内容
 */

@Service
public class BookService {

    /**
     * 此方法之间在监听到消息队列后才调用
     * 此处监听atguigu.news队列,只要此队列接收到消息，方法将会被调用
     * */
    @RabbitListener(queues = "atguigu.news")
    public void receive(Book book){
        System.out.println("收到消息 =============== " + book);
    }


    /**
     * 监听atguigu队列,获取消息头信息
     * */
    @RabbitListener(queues = "atguigu")
    public void receiveHeader(Message message){
        System.out.println("收到消息 =============== " + message.getBody() +"\n============="+ message.getMessageProperties());
    }
}
