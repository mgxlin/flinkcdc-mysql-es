package com.mgxlin.flink.cdc;

import com.ververica.cdc.connectors.mysql.source.MySqlSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class MysqlEventListener implements ApplicationRunner {

    @Resource
    private MySqlSource<String> mySqlSource;
    @Resource
    private CustomSink customSink;

    @Override
    public void run(ApplicationArguments args) {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        try {
            env.enableCheckpointing(3000);

            DataStreamSource<String> mySQLSource = env
                    .fromSource(mySqlSource, WatermarkStrategy.noWatermarks(), "MySQL Source");

            mySQLSource.addSink(customSink).setParallelism(6);

            env.execute("同步品牌数据 MYSQL 到 ES");
        } catch (Exception var5) {
            var5.printStackTrace();
        }
    }
}
