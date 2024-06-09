package com.nhnacademy.minidorray_gateway.domain.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
public class Project {

    private Long id;

    private String name;

    private String status;  // Status : Active, Dormant, End


}
