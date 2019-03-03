package com.todolist.Model;

import java.io.Serializable;

@Data
public class MyListModel implements Serializable {
 
    private static final long serialVersionUID = 1L;
 
    private String title, desc;
 
    public MyListModel() {
        super();
    }
 
    public MyListModel(String title, String name) {
        super();
        this.title = title;
        this.name = name;
    }

}
