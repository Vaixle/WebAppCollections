package com.petushkov.webappcollections.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageResponseDto  {
    @NonNull String message;
}
