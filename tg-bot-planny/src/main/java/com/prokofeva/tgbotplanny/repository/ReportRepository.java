package com.prokofeva.tgbotplanny.repository;

import com.prokofeva.tgbotplanny.entity.ReportDoc;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends MongoRepository<ReportDoc, String> {

//    @Query(value = "find({ '_id': ObjectId( :reportId ) })")
//    Optional<String> getReportById(@Param("reportId") String reportId);
}
