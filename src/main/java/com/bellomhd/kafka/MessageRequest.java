package com.bellomhd.kafka;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageRequest {
    private Long id;
    private String key;
    private String message;
}
