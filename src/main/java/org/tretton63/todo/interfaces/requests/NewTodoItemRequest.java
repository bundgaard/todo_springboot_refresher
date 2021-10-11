package org.tretton63.todo.interfaces.requests;

public class NewTodoItemRequest {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public NewTodoItemRequest(String value) {
        this.value = value;
    }
    public NewTodoItemRequest() {
    }
}
