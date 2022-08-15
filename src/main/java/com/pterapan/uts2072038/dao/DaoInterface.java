package com.pterapan.uts2072038.dao;

import javafx.collections.ObservableList;

import java.util.List;

public interface DaoInterface<T> {
    List<T> getData();
    int addData(T data);
    int delData(T data);
    int updateData(T data);
}
