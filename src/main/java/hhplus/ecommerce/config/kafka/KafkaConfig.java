//package hhplus.ecommerce.config.kafka;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.clients.producer.ProducerRecord;
//import org.apache.kafka.common.header.internals.RecordHeader;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.config.KafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
//import org.springframework.kafka.listener.ContainerProperties;
//import org.springframework.kafka.listener.KafkaMessageListenerContainer;
//import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
//import org.springframework.kafka.requestreply.RequestReplyFuture;
//import org.springframework.kafka.support.KafkaHeaders;
//import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
//
//import java.time.Duration;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.ExecutionException;
//
//
////Post객체를 생성하는 메서드이다. Member정보는 Member서버에서 관리한다.
////
////Post객체에는 MemberId와 MemberName(작성자) 가 필요하다.
////
////물론 요청시에 받아 오는 방법도 있지만 Kafka 비동기 통신이 목적이기 때문에 억지스러운 예제이다.
////KafkaConfig.java
//@Configuration
//public class KafkaConfig {
//
//    @Value("${spring.kafka.bootstrap-servers}")
//    private String bootstrapAddress;
//
//    @Value("${devsoo.kafka.restapi.topic}")
//    private String requestReplyTopic;
//    @Bean
//    public ProducerFactory<String, String> producerFactory(){
//        Map<String, Object> props = new HashMap<>();
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        return new DefaultKafkaProducerFactory<>(props);
//    }
//
//    @Bean
//    public ConsumerFactory<String, String> consumerFactory() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
//        //에러핸들러로 한번 감싼 후 String으로 역직렬화 해준다
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
//        props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
//        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, StringDeserializer.class);
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, "post-server-consumer-group");
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"latest");
//        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,true);
//        return new DefaultKafkaConsumerFactory<>(props);
//    }
//
//    @Bean
//    public KafkaTemplate<String, String> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }
//
//    @Bean
//    public ReplyingKafkaTemplate<String, String, String> replyKafkaTemplate() {
//        ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate = new ReplyingKafkaTemplate<>(producerFactory(), replyContainer());
//        replyingKafkaTemplate.setDefaultReplyTimeout(Duration.ofMillis(5000));
//        return replyingKafkaTemplate;
//    }
//
//    @Bean //컨슈머리스너가 사용할 컨테이너
//    public KafkaMessageListenerContainer<String, String> replyContainer() {
//        //컨슈머를 통해 받아올 토픽을 넣어준다
//        ContainerProperties containerProperties = new ContainerProperties("replyingTopic");
//        return new KafkaMessageListenerContainer<>(consumerFactory(), containerProperties);
//    }
//
//    @Bean
//    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        factory.setBatchListener(false);
//        //poll 지속시간 (지속시간이 끝나면 대기로 바뀜)
//        factory.getContainerProperties().setPollTimeout(1000);
//        //idl상태 지속시간
//        factory.getContainerProperties().setIdleEventInterval(10000L);
//        //동시에 처리되는 메시지의 수(컨슈머 개수)
//        factory.setConcurrency(3);
//        factory.setReplyTemplate(kafkaTemplate());
//        return factory;
//    }
//
//    @Bean
//    public ReplyingKafkaTemplate<String, String, String> replyKafkaTemplate() {
//        //프로듀서의 셋팅과 컨슈머의 셋팅을 합쳐 하나의 템플릿으로 만들기
//        ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate = new ReplyingKafkaTemplate<>(producerFactory(), replyContainer());
//        replyingKafkaTemplate.setDefaultReplyTimeout(Duration.ofMillis(5000));
//        return replyingKafkaTemplate;
//    }
//
//    //replyRecord
//    public Object replyRecord(Object requestData) throws ExecutionException, InterruptedException, JsonProcessingException {
//
//        ObjectMapper objectMapper = null;
//        //객체를 Json형태의 String 값으로 변환
//        String jsonData = objectMapper.writeValueAsString(requestData);
//        //프로듀서 레코드로 변환
//        ProducerRecord<String, String> record = new ProducerRecord<String, String>(kafkaRestApiTopic,jsonData);
//        //리플라이 헤더 추가 밑에서 설명 *1
//        record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, kafkaRestApiTopic.getBytes()));
//        //replyingKafkaTemplate 요청 정보 보내고 응답받기
//        RequestReplyFuture<String, String, String> sendAndReceive = replyingKafkaTemplate.sendAndReceive(record);
//        //응답을 컨슈머레코즈로 만들기
//        ConsumerRecord<String, String> consumerRecord = sendAndReceive.get();
//        // 응답 밸류 반환
//        return consumerRecord.value();
//    }
//
////    데이터 흐름 ->
////    프로듀서레코즈 생성 (프로듀싱 보낼 토픽선택) -> 프로듀서 레코즈 헤더 지정 (셋팅) -> 리플라잉템플릿으로 카프카에 보내기 ->
////    여기 까지가 A 서버 ( 보내는 서버의 역할이다 )
//
//
//}