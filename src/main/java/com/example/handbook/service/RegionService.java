package com.example.handbook.service;

import com.example.handbook.model.Region;
import com.example.handbook.repository.RegionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
  Класс RegionService представляет сервисный слой приложения,
  который обеспечивает доступ к данным, связанным с регионами.

  Он содержит методы для получения, добавления, обновления и удаления регионов,
  а также для проверки существования региона по идентификатору.

  Данные методы могут использовать кэширование результатов для повышения производительности приложения.

  Класс также использует логирование для отображения информации о действиях, производимых сервисным слоем.
 * */

@Service
@Slf4j
public class RegionService {

    private final RegionRepository regionRepository;

    @Autowired
    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Cacheable("regions")
    public List<Region> getAll() {
        log.info("Получение всех регионов");
        return regionRepository.getAll();
    }

    @Cacheable("region")
    public Region getById(Long id) {
        log.info("Получение региона по id: {}", id);
        return regionRepository.getById(id);
    }

    @CacheEvict(value = "regions", allEntries = true)
    public void insert(Region region) {
        log.info("Вставка региона: {}", region);
        regionRepository.insert(region);

    }

    @CacheEvict(value = "regions", allEntries = true)
    public void update(Region region) {
        log.info("Обновление региона: {}", region);
        regionRepository.update(region);
    }

    @CacheEvict(value = "regions", allEntries = true)
    public void deleteById(Long id) {
        log.info("Удаление региона по id: {}", id);
        regionRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        log.info("Проверка существования региона по id: {}", id);
        return regionRepository.existsById(id);
    }
}
