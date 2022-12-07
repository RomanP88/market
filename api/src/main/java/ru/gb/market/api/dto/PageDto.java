package ru.gb.market.api.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class PageDto<T> {
    private List<T> content;
    private Integer pageIndex;
    private Boolean first;
    private Boolean last;
    private Integer pageSize;
    private Integer totalPages;
    private Integer totalElements;

    public PageDto(Integer pageIndex, Integer pageSize, List<T> listElements) {
        if (pageIndex < 0) {
            throw new IllegalArgumentException("Page index must not be less than zero");
        }

        if (pageSize < 1) {
            throw new IllegalArgumentException("Page size must not be less than one");
        }
        this.content = new ArrayList<>();
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.first = pageIndex == 0;
        this.last = pageIndex == listElements.size() / pageSize;
        this.totalPages = (int)Math.ceil((double) listElements.size() / pageSize) ;
        this.totalElements = listElements.size();
        int indexTo;
        if((pageIndex +1)*pageSize < listElements.size()){
            indexTo = (pageIndex +1)*pageSize;
        } else{
            indexTo = listElements.size();
        }
        for (int i = pageIndex*pageSize;i < indexTo; i++){
            this.content.add(listElements.get(i));
        }

    }

    public PageDto() {
        content = new ArrayList<>();
    }
    public <U> PageDto<U> convert(PageDto<T> pageDto, Function<T,U> mapper){
        PageDto<U> temp = new PageDto<>();
        temp.setFirst(pageDto.getFirst());
        temp.setLast(pageDto.getLast());
        temp.setPageIndex(pageDto.getPageIndex());
        temp.setPageSize(pageDto.getPageSize());
        temp.setTotalPages(pageDto.getTotalPages());
        temp.setTotalElements(pageDto.getTotalElements());
        List<U> list = pageDto.getContent()
                .stream()
                .map(mapper)
                .toList();
        temp.setContent(list);
        return temp;
    }

    public List<T> getContent() {
        return this.content;
    }
    public void setContent(List<T> content) {
        this.content = new ArrayList<>(content);
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;

    }

    public Boolean getFirst() {
        return first;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }

    public Boolean getLast() {
        return last;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }
}
