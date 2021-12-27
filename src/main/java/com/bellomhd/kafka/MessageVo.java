package com.bellomhd.kafka;

import lombok.Data;

import java.io.Serializable;

@Data
public class MessageVo implements Serializable {

    private static final long serialVersionUID = 7774345777234994556L;
    private Long id;
    private String message;
}
