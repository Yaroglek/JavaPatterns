package org.yaroglek.patterns.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yaroglek.patterns.domain.Survey;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {
}
