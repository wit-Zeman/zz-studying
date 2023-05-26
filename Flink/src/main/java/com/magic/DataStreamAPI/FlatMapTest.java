package com.magic.DataStreamAPI;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * @Author: zhangzhen
 * @Date: 2023-05-09-15:59
 * @Description:
 */
public class FlatMapTest {
    public static void main(String[] args) throws Exception {
        // 创建执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // 从元素中获取数据
        DataStreamSource<User> userDataStreamSource = env.fromElements(
                User.builder().name("AA").age(18).url("/home").build(),
                User.builder().name("BB").age(19).url("/home").build(),
                User.builder().name("CC").age(20).url("/home").build()
        );
        // 转换
        SingleOutputStreamOperator<String> result = userDataStreamSource.flatMap(new MyFlatMap());

        result.print();

        env.execute();

    }

    public static class MyFlatMap  implements FlatMapFunction<User,String>{

        @Override
        public void flatMap(User user, Collector<String> out) throws Exception {
            out.collect(user.name);
            out.collect(user.age.toString());
            out.collect(user.url);
        }
    }
}
