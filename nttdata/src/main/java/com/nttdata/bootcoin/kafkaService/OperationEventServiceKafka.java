package com.nttdata.bootcoin.kafkaService;


import com.nttdata.bootcoin.events.Event;
import com.nttdata.bootcoin.events.OperationEvent;
import com.nttdata.bootcoin.model.bean.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Component
public class OperationEventServiceKafka {

  @Autowired
  private KafkaTemplate<String, Event<?>> producer;

  @Value("${topic.name:operations}")
  private String topicOperation;

  public void publish(Operation operation){
    log.info("SERVICIOKAFKA.PUBLISH");
    OperationEvent createdEvent = new OperationEvent();
    createdEvent.setData(operation);
    createdEvent.setId(UUID.randomUUID().toString());
    createdEvent.setDate(new Date());
    log.info("SE CREO LAS VARIABLES SERVICIOKAFKA.PUBLISH");
    this.producer.send(topicOperation,"OK" ,createdEvent);
    log.info("SE ENVIO A TOPIC - SERVICIOKAFKA.PUBLISH");
  }

}
