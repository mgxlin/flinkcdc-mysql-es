package com.mgxlin.flink.cdc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "brand-index")
@Data
public class Brand {

    private Long id;

    private String name;

    @JsonProperty("first_letter")
    private String firstLetter;

    private String sort;

    @JsonProperty("factory_status")
    private String factoryStatus;

    @JsonProperty("show_status")
    private String showStatus;

    @JsonProperty("product_count")
    private String productCount;

    @JsonProperty("product_comment_count")
    private String productCommentCount;

    private String logo;

    @JsonProperty("big_pic")
    private String bigPic;

    @JsonProperty("brand_story")
    private String brandStory;
}
