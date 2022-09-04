package com.petushkov.webappcollections.services;

import com.petushkov.webappcollections.dto.ItemFieldValueDto;
import org.springframework.http.ResponseEntity;

public interface FieldInitializeService {

    ResponseEntity<?> setItemFieldValue(Long id,ItemFieldValueDto itemFieldValueDto);
}
