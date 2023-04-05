package com.example.handbook.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
  Класс, представляющий модель сущности "Регион"
 */

@Data
@Alias("regions")
public class Region {
    private Long id;
    private String name;
    private String shortName;
}
