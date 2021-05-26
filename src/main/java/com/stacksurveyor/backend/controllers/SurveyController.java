//	Copyright (C) 2021  StackSurveyor
//
//	This program is free software: you can redistribute it and/or modify
//	it under the terms of the GNU General Public License as published by
//	the Free Software Foundation, either version 3 of the License, or
//	(at your option) any later version.
//
//	This program is distributed in the hope that it will be useful,
//	but WITHOUT ANY WARRANTY; without even the implied warranty of
//	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//	GNU General Public License for more details.
//
//	You should have received a copy of the GNU General Public License
//	along with this program.  If not, see <https://www.gnu.org/licenses/>.

package com.stacksurveyor.backend.controllers;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.stacksurveyor.backend.SurveyErrorCodes;
import com.stacksurveyor.backend.exceptions.SurveyException;
import com.stacksurveyor.backend.forms.SurveyAddForm;
import com.stacksurveyor.backend.models.Survey;
import com.stacksurveyor.backend.repositories.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
public class SurveyController {
    @Autowired
    SurveyRepository surveyRepository;


    @GetMapping("/survey/@me")
    public ResponseEntity<List<Survey>> getAllSurveysOfUser() {
        // TODO Change impl when JWT Authentication is completed
        UUID userId = new UUID(2, 2);

        return new ResponseEntity<>(surveyRepository.findAllByUserId(userId), HttpStatus.ACCEPTED);
    }

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

    @GetMapping(value = "/survey/{id}")
    public ResponseEntity<Survey> getSurvey(@PathVariable("id") UUID id) throws SurveyException {
        Survey survey = surveyRepository.findSurveyById(id);

        if (survey == null) {
            throw new SurveyException(SurveyErrorCodes.SURVEY_NOT_EXIST, "Survey does not exist", 400);
        }

        return new ResponseEntity<>(
                survey, HttpStatus.ACCEPTED
        );
    }

}
