package com.github.duorourou.rediscaffeine2levelcache;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Article {
    private String id;
    private String name;
    private String content;
    private String author;
    private String issueDate;
    private String status;
}
