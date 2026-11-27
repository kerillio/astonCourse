package Step2.Module2;


import Step2.Module2.DTO.EmailMessageDto;
import Step2.Module2.Service.EmailProducerService;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import tools.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext
@EmbeddedKafka(
        partitions = 1,
        topics = {"email-topic"}
)
@TestPropertySource(
        properties = {"spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}"}
)
public class SmtpIntegrationTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EmailProducerService emailProducerService;

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    private Consumer<String, String> testConsumer;

    @BeforeEach
    void setUp() {
        //тестовый консьюмер для чтения JSON
        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps(
                embeddedKafkaBroker, "test-producer-verifier-group", true
        );
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        testConsumer = new DefaultKafkaConsumerFactory<String, String>(consumerProps).createConsumer();

        testConsumer.subscribe(Collections.singletonList("email-topic"));
    }

    @AfterEach
    void tearDown() {
        if (testConsumer != null) {
            testConsumer.close();
        }
    }

    @Test
    void shouldSerializeAndPublishEmailMessageToKafka() throws Exception{
        //1. Вызов продюсера
        String to = "user@example.com";
        String subject = "Создание аккаунта";
        String body = "Поздравляем с созданием аккаунта";

        emailProducerService.sendEmailNotification(to, subject, body);

        //2. читаем запись
        ConsumerRecord<String, String> record = KafkaTestUtils.getSingleRecord(
                testConsumer, "email-topic", Duration.ofSeconds(5)
        );

        //3. Проверка ключа
        assertThat(record.key()).isEqualTo(to);

        //4. Проверка отсутствия заголовка typeId
        assertThat(record.headers().lastHeader("__TypeId__")).isNull();

        //5. Десериализуем JSON обратно в объект и сверяем все поля
        EmailMessageDto emailDto = objectMapper.readValue(record.value(), EmailMessageDto.class);
        Assertions.assertEquals(to, emailDto.to());
        Assertions.assertEquals(subject, emailDto.subject());
        Assertions.assertEquals(body, emailDto.body());
    }

}
