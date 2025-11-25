package com.prokofeva.reportgenerator.entity;

import com.prokofeva.reportgenerator.enums.MessageFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@Document(collection = "reports")
public class ReportDoc {

    @Id
    private String _id;
    @Field(name = "format")
    private MessageFormat format;
    @Field(name = "content")
    private String content;
    @CreatedDate
    private LocalDateTime created;

}
