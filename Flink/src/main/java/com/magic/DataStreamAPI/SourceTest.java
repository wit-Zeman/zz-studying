package com.magic.DataStreamAPI;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.ArrayList;
import java.util.Date;

/**
 * @Author: zhangzhen
 * @Date: 2023-05-08-10:31
 * @Description:
 */
public class SourceTest {
    public static void main(String[] args) throws Exception {
        // 创建执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // 设置并行度
        env.setParallelism(1);
        // 1.从文件中读取数据
        DataStreamSource<String> streamSource = env.readTextFile("D:\\JavaWorkPlace\\zz_studying\\Flink\\input\\clicks.txt");

        streamSource.print();

        // 2.从集合中读取数据
        ArrayList<User> events = new ArrayList<User>();
        events.add(User.builder().name("AAA").url("./goods").date(new Date()).age(12).build());
        events.add(User.builder().name("BBB").url("./order").date(new Date()).age(19).build());
        events.add(User.builder().name("CCC").url("./home").date(new Date()).age(22).build());

        DataStreamSource<User> eventDataStreamSource = env.fromCollection(events);

        // 过滤集合中的元素
        SingleOutputStreamOperator<User> filter = eventDataStreamSource.filter(new FilterFunction<User>() {
            @Override
            public boolean filter(User user) throws Exception {
                return user.age > 18;
            }
        });

        filter.print("过滤");


        // 3.从元素读取数据
        DataStreamSource<Boolean> booleanDataStreamSource =
                env.fromElements(events.add(User.builder().name("AAA").url("./goods").date(new Date()).build()),
                        events.add(User.builder().name("BBB").url("./order").date(new Date()).build()),
                        events.add(User.builder().name("CCC").url("./home").date(new Date()).build())
                        );
        eventDataStreamSource.print();

        // 4、从socket文本流读取数据   新增port要关闭防火墙！！！！！！！！！
        DataStreamSource<String> socketSource = env.socketTextStream("192.168.10.100", 7777);
        socketSource.print();


        // 5、读取kafka消息数据
        env.execute();

    }
}
