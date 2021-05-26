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
import com.stacksurveyor.backend.utils.errors.SurveyErrorCodes;
import com.stacksurveyor.backend.exceptions.SurveyException;
import com.stacksurveyor.backend.structs.requests.SurveyCreateForm;
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
@RequestMapping("/surveys")
public class SurveyController {
    @Autowired
    SurveyRepository surveyRepository;

    @GetMapping("/@me")
    public ResponseEntity<List<Survey>> getAllSurveysOfUser() {
        // TODO Change impl when JWT Authentication is completed
        UUID userId = new UUID(2, 2);

        return new ResponseEntity<>(surveyRepository.findAllByUserId(userId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Survey> createSurvey(@RequestBody SurveyCreateForm surveyCreateFormForm) {
        // TODO Change impl when JWT Authentication is completed
        UUID userId = new UUID(2, 2);

        Survey survey = surveyRepository.save(new Survey(Uuids.timeBased(), userId,
                surveyCreateFormForm.getTitle(), surveyCreateFormForm.getDescription()));

        return new ResponseEntity<>(
                survey,
                HttpStatus.CREATED
        );
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Survey> getSurvey(@PathVariable("id") String id) throws SurveyException {
        Survey survey = surveyRepository.findSurveyById(UUID.fromString(id));

        if (survey == null) {
            throw new SurveyException(SurveyErrorCodes.SURVEY_NOT_EXIST, "Survey does not exist", 400);
        }

        return new ResponseEntity<>(
                survey, HttpStatus.OK
        );
    }

}
