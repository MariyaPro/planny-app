package com.prokofeva.tgbotplanny.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Setter
@Getter
@Builder
@Document(collection = "users_tg")
public class UserTG {

    @Id
    private ObjectId _id;

    private @NonNull Long idTg;
    private @NonNull String firstNameTg;
    private String lastNameTg;
    private @NonNull Boolean isBot;
    private String userNameTg;
    private String languageCode;

    private List<UserTG> subscribers;
    private List<UserTG> subscriptions;

}
