package com.relaximus.eventhubreactor;

import com.azure.core.util.BinaryData;
import com.azure.messaging.eventhubs.EventData;
import com.azure.messaging.eventhubs.EventHubProducerAsyncClient;
import com.relaximus.eventhubreactor.EventHubReactorApplication.RawData;
import java.util.List;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MockData {

    final private EventHubProducerAsyncClient client;

    public MockData(EventHubProducerAsyncClient client) {
        this.client = client;
    }

    public Mono<Void> send(RawData data) {
        return this.send(List.of(data));
    }

    public Mono<Void> send(List<RawData> data) {
        var events = data.stream().map(BinaryData::fromObject).map(EventData::new).toList();
        return client.send(events);
    }
}
