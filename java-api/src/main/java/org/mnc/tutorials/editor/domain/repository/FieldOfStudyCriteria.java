package org.mnc.tutorials.editor.domain.repository;

public class FieldOfStudyCriteria {
    private String nameLike;
    private String descriptionContain;
    private Boolean isApproved;
    private SortCriteria sorting;
    private int page = 1;
    private int size = 10;

    public FieldOfStudyCriteria(String nameLike, String descriptionContain, Boolean isApproved, SortCriteria sorting, int page, int size) {
        this.nameLike = nameLike;
        this.descriptionContain = descriptionContain;
        this.isApproved = isApproved;
        this.sorting = sorting;
        this.page = page;
        this.size = size;
    }

    public String getNameLike() {
        return nameLike;
    }

    public void setNameLike(String nameLike) {
        this.nameLike = nameLike;
    }

    public String getDescriptionContain() {
        return descriptionContain;
    }

    public void setDescriptionContain(String descriptionContain) {
        this.descriptionContain = descriptionContain;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    public SortCriteria getSorting() {
        return sorting;
    }

    public void setSorting(SortCriteria sorting) {
        this.sorting = sorting;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
