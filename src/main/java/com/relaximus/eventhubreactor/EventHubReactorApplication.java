package com.relaximus.eventhubreactor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import reactor.core.publisher.Flux;

@Slf4j
@SpringBootApplication
public class EventHubReactorApplication {

    @Autowired
    MockData mockData;

    public static void main(String[] args) {
        SpringApplication.run(EventHubReactorApplication.class, args);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void onStart() {
        sendMockData()
                .blockLast();
    }

    private Flux<Void> sendMockData() {
        final Random random = new Random();
        final var start = LocalDateTime.now();
        // 300 seconds
        return Flux.range(0, 300)
                .delayElements(Duration.ofSeconds(1))
                .map(start::plusSeconds)

                // creating JSON message with random values for every day
                .map(day -> new RawData(day, random.nextDouble(100.0)))
                .doOnNext(b -> log.info("Sending {}", b))
                .flatMap(mockData::send);
    }

    static public record RawData(LocalDateTime timestamp, Double value) {

    }
}
