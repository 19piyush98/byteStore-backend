package com.org.bytestore.controller;

import com.org.bytestore.model.request.GetLogRequest;
import com.org.bytestore.model.response.GetLogResponse;
import com.org.bytestore.service.AuditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/byteStore/audit")
@RequiredArgsConstructor
@Slf4j
public class AuditController {

    private final AuditService auditService;

    @PostMapping("/getAllAudits")
    public ResponseEntity<Optional<GetLogResponse>> getAllAudits(@RequestBody GetLogRequest request) {
        Optional<GetLogResponse> getAllAudits = auditService.getLogs(request);
        return ResponseEntity.ok(getAllAudits);
    }
}

