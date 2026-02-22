package org.mnc.tutorials.editor.domain.repository;

import org.springframework.data.domain.Sort;

public class SortCriteria {
    private Direction direction;
    private String sortBy;

    public SortCriteria() {
    }

    public SortCriteria(Direction direction, String sortBy) {
        this.direction = direction;
        this.sortBy = sortBy;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public enum Direction{
        ASC,
        DESC
    }
}
