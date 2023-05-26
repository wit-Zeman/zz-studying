package com.magic.DataStreamAPI;

import org.apache.flink.connector.jdbc.JdbcConnectionOptions;
import org.apache.flink.connector.jdbc.JdbcSink;
import org.apache.flink.connector.jdbc.JdbcStatementBuilder;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author: zhangzhen
 * @Date: 2023-05-09-16:27
 * @Description:
 */
public class SinkToMysql {
    public static void main(String[] args) throws Exception {
        // 创建执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // 从元素中获取数据
        DataStreamSource<User> userDataStreamSource = env.fromElements(User.builder().name("AA").age(18).url("/home").build(),
                User.builder().name("BB").age(19).url("/home").build(), User.builder().name("CC").age(20).url("/home").build());

        // 调用addsink()
        //JdbcSink.sink(1,2,3)  1: sql 2: JdbcStatementBuilder 问号匹配字段 3: mysql连接配置
        userDataStreamSource.addSink(JdbcSink.sink("insert into user (name,age,url) values(?,?,?)", new JdbcStatementBuilder<User>() {
                    @Override
                    public void accept(PreparedStatement preparedStatement, User user) throws SQLException {
                                preparedStatement.setString(1,user.name);
                                preparedStatement.setInt(2,user.age);
                                preparedStatement.setString(3,user.url);
                    }
                },
                new JdbcConnectionOptions.JdbcConnectionOptionsBuilder().withUrl("jdbc:mysql://localhost:3306/flink").withDriverName("com"
                        + ".mysql.jdbc.Driver").withUsername("root").withPassword("123456").build()));

        env.execute();
    }
}
