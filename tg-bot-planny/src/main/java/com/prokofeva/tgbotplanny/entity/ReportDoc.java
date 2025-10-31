package com.prokofeva.tgbotplanny.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Setter
@Getter
@Document(collection = "reports")
@NoArgsConstructor
public class ReportDoc {

    @Id
    private String _id;
    @Field(name = "content")
    private String content;

}
