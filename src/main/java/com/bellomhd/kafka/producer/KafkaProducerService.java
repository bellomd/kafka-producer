package com.bellomhd.kafka.producer;

import com.bellomhd.kafka.MessageVo;
import org.springframework.kafka.support.SendResult;

public interface KafkaProducerService {

    void publish(String topic, String key, MessageVo messageVo);

    SendResult<String, MessageVo> publishSync(String topic, String key, MessageVo messageVo);
}
