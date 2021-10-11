package org.tretton63.todo.interfaces.requests;

public class CacheValueDto {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public CacheValueDto(String value) {
        this.value = value;
    }
    public CacheValueDto() {

    }
}
