package com.magic.DataStreamAPI;

import org.apache.commons.collections.IteratorUtils;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.datastream.WindowedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import java.util.Iterator;
import java.util.List;

/**
 * @Author: zhangzhen
 * @Date: 2023-05-10-12:08
 * @Description:
 */
public class WindowTest {
    public static void main(String[] args) throws Exception {
        //1.获取流的执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        //2.从端口获取数据
        DataStreamSource<String> streamSource = env.socketTextStream("192.168.10.100", 9999);

        //3.将数据转为Tuple
        SingleOutputStreamOperator<Tuple2<String, Integer>> wordToOneDStream = streamSource.flatMap(new FlatMapFunction<String,
                Tuple2<String, Integer>>() {
            @Override
            public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
                String[] split = value.split(" ");
                for (String s : split) {
                    out.collect(Tuple2.of(s, 1));
                }
            }
        });


        //TODO keyBy 要想调用window方法必须keyBy
        KeyedStream<Tuple2<String, Integer>, String> keyedStream = wordToOneDStream.keyBy(new KeySelector<Tuple2<String, Integer>,
                String>() {
            @Override
            public String getKey(Tuple2<String, Integer> r) throws Exception {
                return r.f0;
            }
        });

        //TODO 调用window()方法  开启一个基于处理时间的5s滚动窗口
        //window()方法中,传入窗口分配器 得到WindowedStream
        WindowedStream<Tuple2<String, Integer>, String, TimeWindow> windowedStream =
                keyedStream.window(TumblingProcessingTimeWindows.of(Time.seconds(5)));

        //TODO 窗口函数(全量聚合)
        //WindowFunction<Tuple2<String, Integer>, Integer, String, TimeWindow>
        //Tuple2:输入 Integer:输出 String: key
        windowedStream.apply(new WindowFunction<Tuple2<String, Integer>, Tuple3<Long, Long, Integer>, String, TimeWindow>() {
            @Override
            public void apply(String key, TimeWindow window, Iterable<Tuple2<String, Integer>> input, Collector<Tuple3<Long, Long,
                    Integer>> out) throws Exception {
                Iterator<Tuple2<String, Integer>> iterator = input.iterator();
                List list = IteratorUtils.toList(iterator);
                long windowEnd = window.getEnd();
                long windowStart = window.getStart();
                out.collect(new Tuple3<Long, Long, Integer>(windowStart, windowEnd, list.size()));
            }
        }).print();


        env.execute();
    }

}
