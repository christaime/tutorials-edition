package org.mnc.tutorials.editor.infrastructure.persistence.adapter;

import org.mnc.tutorials.editor.domain.repository.SortCriteria;
import org.springframework.data.domain.Sort;

public class SortingUtils {
    public static Sort getSort(SortCriteria sortCriteria){
        return  Sort.by(Sort.Direction.fromString(
                        sortCriteria.getDirection() != null ?  sortCriteria.getDirection().name() : "ASC"),
                sortCriteria.getSortBy() != null && !sortCriteria.getSortBy().isBlank() ? sortCriteria.getSortBy() : "id"
        );
    }
}
