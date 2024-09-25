package com.mgxlin.flink.cdc;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ESRepository extends ElasticsearchRepository<Brand,Long> {
}
