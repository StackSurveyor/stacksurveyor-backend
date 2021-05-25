package com.stacksurveyor.backend.controllers;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.stacksurveyor.backend.forms.SurveyAddForm;
import com.stacksurveyor.backend.models.Survey;
import com.stacksurveyor.backend.repositories.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@CrossOrigin
@RestController
public class SurveyController {
    @Autowired
    SurveyRepository surveyRepository;

    @PostMapping("/survey")
    public ResponseEntity<Survey> createSurvey(@RequestBody SurveyAddForm surveyAddForm) {
        // TODO Change impl when JWT Authentication is completed
        UUID userId = new UUID(2, 2);

        Survey survey = new Survey(Uuids.timeBased(), userId,
                surveyAddForm.getTitle(), surveyAddForm.getDescription());

        surveyRepository.save(survey);

        return new ResponseEntity<>(
                survey,
                HttpStatus.CREATED
        );
    }

}
