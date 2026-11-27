package Step2.Module2.Service;

import Step2.Module2.DTO.EmailMessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmailProducerService {

    private static final Logger log = LoggerFactory.getLogger(EmailProducerService.class);
    private static final String TOPIC = "email-topic";
    private final KafkaTemplate<String, EmailMessageDto> kafkaTemplate;

    public EmailProducerService(KafkaTemplate<String, EmailMessageDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEmailNotification(String to, String subject, String body) {
        EmailMessageDto messageDto = new EmailMessageDto(to, subject, body);

        kafkaTemplate.send(TOPIC, to, messageDto)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        log.info("Уведомление успешно отправлено");
                    } else {
                        log.warn("Уведомление не отправлено. Ошибка: ", ex.getMessage());
                    }
                });
    }

}
