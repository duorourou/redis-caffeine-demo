package com.github.duorourou.rediscaffeine2levelcache;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Author {
    private String id;
    private String name;
    private String email;
}
