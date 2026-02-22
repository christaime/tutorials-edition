package org.mnc.tutorials.editor.domain.model;

import java.util.List;

public record DomainPage<T>(
        List<T> content,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages
) {}