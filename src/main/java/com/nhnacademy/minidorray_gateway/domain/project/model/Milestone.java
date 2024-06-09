package com.nhnacademy.minidorray_gateway.domain.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
public class Milestone {

    private Long id;


    private String name;

    private ZonedDateTime startDate;


    private ZonedDateTime dueDate;


}
