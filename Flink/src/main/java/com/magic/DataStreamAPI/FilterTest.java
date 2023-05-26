package com.magic.DataStreamAPI;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @Author: zhangzhen
 * @Date: 2023-05-09-15:47
 * @Description:
 */
public class FilterTest {
    public static void main(String[] args) throws Exception {

        // 创建执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // 从元素中获取数据
        DataStreamSource<User> userDataStreamSource = env.fromElements(
                User.builder().name("AA").age(18).url("/home").build(),
                User.builder().name("BB").age(19).url("/home").build(),
                User.builder().name("CC").age(20).url("/home").build()
        );
        // 转换  过滤user.age 大于 18
        SingleOutputStreamOperator<User> result = userDataStreamSource.filter(new FilterFunction<User>() {
            @Override
            public boolean filter(User user) throws Exception {
                return user.age > 18;
            }
        });

        // 打印结果
        result.print();
        // 执行
        env.execute();
    }



}
