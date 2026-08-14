package com.streamx.contentservice.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoEncodedEvent {

    private String movieId;
    private boolean success;
    private String errorMessage;
}
