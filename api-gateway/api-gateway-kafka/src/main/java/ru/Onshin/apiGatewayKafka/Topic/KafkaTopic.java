package ru.Onshin.apiGatewayKafka.Topic;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {

    @Bean
    public NewTopic topicCreateCat() {
        return TopicBuilder.name("topic-create-cat").build();
    }

    @Bean
    public NewTopic topicUpdateCat() {
        return TopicBuilder.name("topic-update-cat").build();
    }

    @Bean
    public NewTopic topicDeleteCat() {
        return TopicBuilder.name("topic-delete-cat").build();
    }

    @Bean
    public NewTopic topicCreateOwner() {
        return TopicBuilder.name("topic-create-owner").build();
    }

    @Bean
    public NewTopic topicUpdateOwner() {
        return TopicBuilder.name("topic-update-owner").build();
    }

    @Bean
    public NewTopic topicDeleteOwner() {
        return TopicBuilder.name("topic-delete-owner").build();
    }

    @Bean
    public NewTopic topicAdminDeleteOwner() {
        return TopicBuilder.name("topic-admin-delete-owner").build();
    }
}
