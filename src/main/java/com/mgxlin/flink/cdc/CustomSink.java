package com.mgxlin.flink.cdc;

import cn.hutool.extra.spring.SpringUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class CustomSink implements SinkFunction<String> {

    // 提前创建 ObjectMapper 实例
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void invoke(String value, Context context) throws Exception {
        JsonNode jsonNode = objectMapper.readTree(value);

        // 获取操作
        String option = jsonNode.get("op").asText();

        // 只针对 新增 与 更新 操作
        if ("c".equals(option) || "u".equals(option)) {
            JsonNode after = jsonNode.get("after");

            // 打印日志
            log.info("Processing brand update or creation: {}", after.toString());

            // 将 JSON 转换为 Brand 对象
            Brand brand = objectMapper.convertValue(after, Brand.class);

            // 获取 ESRepository
            ESRepository esRepository = SpringUtil.getBean(ESRepository.class);

            // 保存到 Elasticsearch
            esRepository.save(brand);
        }
    }

}
