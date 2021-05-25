package com.stacksurveyor.backend.repositories;

import com.stacksurveyor.backend.models.Survey;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;
import java.util.UUID;

public interface SurveyRepository extends CassandraRepository<Survey, UUID> {
    @AllowFiltering
    List<Survey> findAllByUserId(UUID userId);

    @AllowFiltering
    Survey findSurveyById(UUID id);
}
