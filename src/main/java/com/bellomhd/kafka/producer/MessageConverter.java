package com.bellomhd.kafka.producer;

import com.bellomhd.kafka.MessageRequest;
import com.bellomhd.kafka.MessageVo;
import org.springframework.stereotype.Component;

@Component
public class MessageConverter {

    public MessageVo convert(final MessageRequest messageRequest) {
        final MessageVo messageVo = new MessageVo();
        messageVo.setId(messageRequest.getId());
        messageVo.setMessage(messageRequest.getMessage());
        return messageVo;
    }
}
