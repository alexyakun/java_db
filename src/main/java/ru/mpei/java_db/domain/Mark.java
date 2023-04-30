package ru.mpei.java_db.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Mark {
    private final long id;
    private final String course_name;
    private final int value;

}
