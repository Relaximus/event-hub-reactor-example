package com.relaximus.eventhubreactor;

import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.messaging.eventhubs.EventHubConsumerAsyncClient;
import com.azure.messaging.eventhubs.EventHubProducerAsyncClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Value("${hub.connection-string}")
    private String connectionString;
    @Value("${hub.name}")
    private String hubName;

    @Bean
    public EventHubClientBuilder builder() {
        return new EventHubClientBuilder()
                .connectionString(connectionString, hubName);
    }

    @Bean
    public EventHubProducerAsyncClient senderClient() {
        return builder()
                .buildAsyncProducerClient();
    }

    @Bean
    public EventHubConsumerAsyncClient consumerClient() {
        return builder()
                .consumerGroup(EventHubClientBuilder.DEFAULT_CONSUMER_GROUP_NAME)
                .buildAsyncConsumerClient();
    }

}
