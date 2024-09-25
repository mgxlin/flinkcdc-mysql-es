package com.mgxlin.flink.cdc;

import com.ververica.cdc.connectors.mysql.source.MySqlSource;
import com.ververica.cdc.connectors.mysql.table.StartupOptions;
import com.ververica.cdc.debezium.JsonDebeziumDeserializationSchema;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

    @Bean
    public MySqlSource<String> mySqlSource() {
        return MySqlSource.<String>builder()
                .hostname("localhost")
                .port(3306)
                .databaseList("mgxlin")
                .tableList("mgxlin.pms_brand")
                .username("root")
                .password("root")
                .deserializer(new JsonDebeziumDeserializationSchema())
                .startupOptions(StartupOptions.earliest())
                .build();
    }
}
