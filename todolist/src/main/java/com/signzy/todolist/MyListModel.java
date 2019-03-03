package com.signzy.todolist;

import java.io.Serializable;


/**
 * MyListModel to map the data.
 * @author Gayatri
 *
 */
public class MyListModel implements Serializable {
 
    private static final long serialVersionUID = 1L;
 
    private String title, desc;
    private int mode;
 
    public MyListModel() {
        super();
    }
 
    public MyListModel(String title, String desc,int mode) {
        super();
        this.title = title;
        this.desc = desc;
        this.mode = mode;
    }

    public String getTitle() {
        return title;
    }
 
    public void setTitle(String title) {
        this.title = title;
    }
 
    public String getDesc() {
        return desc;
    }
 
    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public int getMode() {
        return mode;
    }
 
    public void setMode(int mode) {
        this.mode = mode;
    }
}
