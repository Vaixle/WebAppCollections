package com.petushkov.webappcollections.services.impl;

import com.petushkov.webappcollections.models.FieldInitialize;
import com.petushkov.webappcollections.repositories.FieldInitializeRepository;
import com.petushkov.webappcollections.services.FieldInitializeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


//@Service
//@AllArgsConstructor
//public class FieldInitializeServiceImpl implements FieldInitializeService {
//
//    private FieldInitializeRepository fieldInitializeRepository;
//
//    @Override
//    public ResponseEntity<?> setItemFieldValue(Long id, ItemFieldDto itemFieldValueDto) {
//
//        FieldInitialize fieldInitialize = fieldInitializeRepository.findById(id).get();
//
//        String value = itemFieldValueDto.getValue();
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//        switch (fieldInitialize.getType()){
//            case "number": fieldInitialize.setNumber(Long.parseLong(value));
//                break;
//            case "text": fieldInitialize.setText(value);
//                break;
//            case "textarea": fieldInitialize.setTextarea(value);
//                break;
//            case "logic": fieldInitialize.setLogic(value);
//                break;
//            case "date": fieldInitialize.setDate(LocalDate.parse(value,formatter));
//                break;
//        }
//
//        fieldInitializeRepository.save(fieldInitialize);
//
//        return ResponseEntity.ok().build();
//    }
//
//}
