package com.example.handbook.repository;

import com.example.handbook.model.Region;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
  Интерфейс для доступа к данным регионов.
  */

@Mapper
public interface RegionRepository {

    /**
     Получает все регионы из базы данных.
     @return список регионов.
     */
    @Select("SELECT id, name, short_name as shortName FROM regions")
    List<Region> getAll();

    /**
     Получает регион по его id.
     @param id идентификатор региона.
     @return найденный регион или null, если регион с указанным id не найден.
     */
    @Select("SELECT id, name, short_name as shortName FROM regions WHERE id=#{id}")
    Region getById(Long id);

    /**
     Вставляет новый регион в базу данных.
     @param region регион, который нужно вставить.
     */
    @Insert("INSERT INTO regions (name, short_name) VALUES (#{name}, #{shortName})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Region region);

    /**
     Обновляет существующий регион в базе данных.
     @param region регион, который нужно обновить.
     */
    @Update("UPDATE regions SET name=#{name}, short_name=#{shortName} WHERE id=#{id}")
    void update(Region region);

    /**
     Удаляет регион с указанным id из базы данных.
     @param id идентификатор региона, который нужно удалить.
     */
    @Delete("DELETE FROM regions WHERE id=#{id}")
    void deleteById(Long id);

    /**
     Проверяет существование региона с указанным id в базе данных.
     @param id идентификатор региона.
     @return true, если регион с указанным id существует в базе данных, false в противном случае.
     */
    @Select("SELECT EXISTS (SELECT 1 FROM regions WHERE id=#{id})")
    boolean existsById(Long id);
}
