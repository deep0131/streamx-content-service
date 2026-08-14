package com.streamx.contentservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class VideoUploadedEncodedEventConsumer {
    
    private final ContentService contentService;

    @KafkaListener(
            topics = "video.uploaded"
    )
    public void consumeVideoUploadedEvent(@Payload Map<String, Object> payload) {

        String movieId = (String) payload.get("movieId");
        String videoKey = (String) payload.get("videoKey");

        log.info("Consuming video.uploaded event for movie: {}", movieId);

        try {
            contentService.updateVideoKey(movieId, videoKey);
        }
        catch (Exception e) {
            log.error("Error processing video.uploaded event for movie: {}", movieId, e);
        }
    }

    @KafkaListener(
            topics = "video.encoded"
    )
    public void consumeVideoEncodedEvent(@Payload Map<String, Object> payload) {

        String movieId = (String) payload.get("movieId");
        boolean success = (Boolean) payload.get("success");

        log.info("Consuming video.encoded event for movie: {}", movieId);

        try {
            contentService.updateEncodingStatus(movieId, success);
        }
        catch (Exception e) {
            log.error("Error processing video.encoded event for movie: {} : {}",
                    movieId, payload.get("errorMessage"), e);
        }
    }
}
