package Step2.Module2.Kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "test-topic", groupId = "study-group")
    public void consume(String message) {
        System.out.println(">>>Arrived from Kafka: " + message);
    }

}
