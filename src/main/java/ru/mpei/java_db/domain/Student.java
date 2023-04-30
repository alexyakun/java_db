package ru.mpei.java_db.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Student {
    private final long id;
    private final String name;
    private final String surname;
    private final String otchestvo;
    private final String groupName;
    private final int year;

}
