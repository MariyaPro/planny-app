package com.prokofeva.reportgenerator.repository;

import com.prokofeva.reportgenerator.entity.ReportDoc;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends MongoRepository<ReportDoc, String> {


}
