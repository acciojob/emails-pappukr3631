package com.driver;

import java.util.Comparator;

public class SortByEndTime implements Comparator<Meeting> {
    public int compare(Meeting a, Meeting b) {
        return a.getEndTime().compareTo(b.getEndTime());
    }
}
