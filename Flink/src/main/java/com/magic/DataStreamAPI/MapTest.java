package com.magic.DataStreamAPI;

import jdk.internal.dynalink.linker.LinkerServices;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @Author: zhangzhen
 * @Date: 2023-05-09-14:44
 * @Description:
 */
public class MapTest {
    public static void main(String[] args) throws Exception {

        // 创建执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // 从元素中获取数据
        DataStreamSource<User> userDataStreamSource = env.fromElements(
                User.builder().name("AA").age(18).url("/home").build(),
                User.builder().name("BB").age(19).url("/home").build(),
                User.builder().name("CC").age(20).url("/home").build()
        );
        // 转换  获取user中的name字段
        SingleOutputStreamOperator<String> result = userDataStreamSource.map(new UserMap());

        // 打印结果
        result.print();
        // 执行
        env.execute();
    }


    // 自定义map 第一个参数是 传入的是数据类型 传出的是输出的数据类型
    public static class UserMap implements MapFunction<User,String>{

        @Override
        public String map(User user) throws Exception {
            return user.name;
        }
    }
}
