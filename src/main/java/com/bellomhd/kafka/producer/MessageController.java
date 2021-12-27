package com.bellomhd.kafka.producer;

import com.bellomhd.kafka.MessageRequest;
import com.bellomhd.kafka.MessageVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Value("${kafka.topic.name}")
    private String topic;

    private final KafkaProducerService kafkaProducerService;
    private final MessageConverter messageConverter;

    public MessageController(KafkaProducerService kafkaProducerService,
                             MessageConverter messageConverter) {
        this.kafkaProducerService = kafkaProducerService;
        this.messageConverter = messageConverter;
    }

    @PostMapping("/kafka/messages/async")
    void send(@RequestBody MessageRequest messageRequest) {
        final MessageVo messageVo = messageConverter.convert(messageRequest);
        kafkaProducerService.publish(topic, messageRequest.getKey(), messageVo);
    }

    @PostMapping("/kafka/messages/sync")
    void sendSync(@RequestBody MessageRequest messageRequest) {
        final MessageVo messageVo = messageConverter.convert(messageRequest);
        kafkaProducerService.publishSync(topic, messageRequest.getKey(), messageVo);
    }
}
