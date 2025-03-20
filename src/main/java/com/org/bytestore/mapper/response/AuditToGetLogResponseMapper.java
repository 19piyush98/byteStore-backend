package com.org.bytestore.mapper.response;

import com.org.bytestore.model.entity.Audit;
import com.org.bytestore.model.response.GetLogResponse;
import com.org.bytestore.model.response.GetLogResponse.Log;
import com.org.bytestore.utils.GsonUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuditToGetLogResponseMapper {

    public GetLogResponse map(final List<Audit> audits) {
        final List<Log> logs = audits.stream().map(this::map).collect(Collectors.toList());
        return GetLogResponse.builder()
                .logs(logs)
                .message("Getting all audit successfully...")
                .success(Boolean.TRUE)
                .build();
    }

    private Log map(final Audit audit) {
        String auditMessage = audit.getUserName();
        auditMessage = switch (audit.getOperationType()) {
            case CREATE -> String.join(" ", auditMessage, "added");
            case UPDATE -> String.join(" ", auditMessage, "updated");
            case DELETE -> String.join(" ", auditMessage, "deleted");
        };

        auditMessage = switch (audit.getOperationEntity()) {
            case USER -> String.join(" ", auditMessage, "user ");
            case ORG -> String.join(" ", auditMessage, "org details ");
            case PRODUCT -> String.join(" ", auditMessage, "product ");
        };
        System.out.println(auditMessage);
        auditMessage += audit.getChanges();

        return Log.builder()
                .logId(audit.getLogId())
                .logMessage(auditMessage)
                .timestamp(audit.getTimestamp())
                .build();
    }
}
