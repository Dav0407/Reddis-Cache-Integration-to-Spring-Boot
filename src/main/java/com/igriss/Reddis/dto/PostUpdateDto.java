package com.igriss.Reddis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUpdateDto implements Serializable {
    private Integer id;
    private String title;
    private String body;
}
