package com.org.bytestore.controller;

import com.org.bytestore.model.entity.Organisation;
import com.org.bytestore.model.request.AddOrganisationRequest;
import com.org.bytestore.model.request.GetOrganisationRequest;
import com.org.bytestore.model.request.UpdateOrganisationRequest;
import com.org.bytestore.model.response.AddOrganisationResponse;
import com.org.bytestore.model.response.GetOrganisationResponse;
import com.org.bytestore.model.response.UpdateOrganisationResponse;
import com.org.bytestore.service.OrganisationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/byteStore/organisation")
@RequiredArgsConstructor
@Slf4j
public class OrganisationController {

    private final OrganisationService organisationService;

    @PostMapping("/updateOrg")
    public ResponseEntity<Optional<UpdateOrganisationResponse>> updateOrg(@RequestBody UpdateOrganisationRequest request){
        Optional<UpdateOrganisationResponse> response = organisationService.updateOrganisation(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/getOrg")
    public ResponseEntity<GetOrganisationResponse> getOrg (@RequestBody GetOrganisationRequest request){
        GetOrganisationResponse getOrganisationResponse = organisationService.getOrganisationResponse(request);
        return ResponseEntity.ok(getOrganisationResponse);
    }
}
