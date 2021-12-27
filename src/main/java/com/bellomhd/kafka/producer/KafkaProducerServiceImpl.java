package com.bellomhd.kafka.producer;

import com.bellomhd.kafka.MessageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {

    private final KafkaTemplate<String, MessageVo> kafkaTemplate;

    public KafkaProducerServiceImpl(KafkaTemplate<String, MessageVo> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(String topic, String key, MessageVo messageVo) {
        kafkaTemplate.send(topic, key, messageVo);
    }

    @Override
    public SendResult<String, MessageVo> publishSync(String topic, String key, MessageVo messageVo) {
        try {
            return kafkaTemplate.send(topic, key, messageVo).get();
        } catch (ExecutionException | InterruptedException e) {
            log.error("Exception while publishing message to topic: {}, key: {}, \n{}", topic, key, e);
            throw new RuntimeException("Exception while publishing message");
        } catch (Exception e) {
            log.error("Unexpected exception while publishing message to topic: {}, key: {}, \n{}", topic, key, e);
            throw new RuntimeException("Unexpected exception while publishing message");
        }
    }
}
