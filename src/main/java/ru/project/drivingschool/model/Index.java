package ru.project.drivingschool.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Index {

    public static final int DEF_PAGE = 0;
    public static final int DEF_LIMIT = 100;
    public static final int DEF_TOTAL = 1;

    private int limit; //кол-во строк на странице
    private int offset; // текущая страница
    private int total; // всего страниц

    public Index() {
        this.limit = DEF_LIMIT;
        this.offset = DEF_PAGE;
        this.total = DEF_TOTAL;
    }
}
