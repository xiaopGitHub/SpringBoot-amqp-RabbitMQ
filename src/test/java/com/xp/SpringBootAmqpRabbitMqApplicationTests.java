package com.xp;

import com.xp.entity.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootAmqpRabbitMqApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    AmqpAdmin amqpAdmin;

    /**
     * 创建Exchange
     * */
    @Test
    public void createExchange(){
/*        amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
        System.out.println("创建完成。。。。");*/
        amqpAdmin.declareQueue(new Queue("amqpadmin.queue", true));

        //创建绑定规则
        amqpAdmin.declareBinding(new Binding("amqpadmin.queue",
                Binding.DestinationType.QUEUE,
                "amqpadmin.exchange",
                "amqp.haha",
                null));
    }

    /**
     * 测试发送消息：
     * 1.单播模式下点对点消息
     * */
    @Test
    public void contextLoads() {
        /**
         * Message需要自己定义;可以定制消息体内容，和消息头
         * rabbitTemplate.send(, , );
         *
         * 直接写入发送的消息对象,自动将其序列化，
         * 保存发送给RabbitMQ,默认当成消息体
         *
         * */
        Map<String,Object> map=new HashMap<>();
        map.put("msg", "这是springboot整合rabbitmq发送过来的消息");
        map.put("data", Arrays.asList("hello","hi",123,true));
        //对象被默认序列化后发送出去,接受的的是rabbitMQ默认的messageConverter转化后的数据
        //可以自定义messageConverter将数据对象转化为json格式发送出去
        rabbitTemplate.convertAndSend("exchange.direct",
                                     "atguigu.news" ,
                                      new Book("自然哲学","牛顿") );
    }

    /**
     * 测试接收消息：
     *
     * */
    @Test
    public void receive(){
        //接收指定队列的消息
        Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }


    /**
     * 测试广播模式发送,只需改变交换器即可
     *
     * */
    @Test
    public void radioMsg(){
        Map<String,Object> map=new HashMap<>();
        map.put("msg", "这是springboot整合rabbitmq发送过来的消息");
        map.put("data", Arrays.asList("hello","hi",123,true));
        //对象被默认序列化后发送出去,接受的的是rabbitMQ默认的messageConverter转化后的数据
        //可以自定义messageConverter将数据对象转化为json格式发送出去
        rabbitTemplate.convertAndSend("exchange.fanout","",
                                      new Book("自然哲学","牛顿"))   ;
    }
}
