package com.org.bytestore.model.entity;

import com.org.bytestore.enums.OrganisationStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Getter
@Setter
@Document("Organisation")
public class Organisation {

    @Id
    private String orgTokenId;

    private String orgName;

    private String email;

    private Long phoneNo;

    private String street;

    private String city;

    private String state;

    private String country;

    private Long zipCode;

    private String website;

    private String description;

    private OrganisationStatus status;

    private Long createdAt;

    private Long updatedAt;

}
