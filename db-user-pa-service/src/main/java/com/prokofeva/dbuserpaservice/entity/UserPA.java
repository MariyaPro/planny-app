package com.prokofeva.dbuserpaservice.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Setter
@Getter
@Builder
@Document(collection = "users_pa")
@NoArgsConstructor
@AllArgsConstructor
public class UserPA {

    @Id
    private String _id;

    private Long idTg;
    private @NotEmpty String firstNameTg;
    private String lastNameTg;
    private @NotNull String nameForReport;
    private @NotNull boolean isBot;
    private String userNameTg;
    private String languageCode;
    private String description;
    private boolean active;

    //    private List<UserTG> subscribersId;
    private List<String> subscriptionsId;

}
