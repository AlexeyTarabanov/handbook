package com.example.handbook.controller;

import com.example.handbook.model.Region;
import com.example.handbook.service.RegionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
  Контроллер, отвечающий за операции с регионами.
 */

@RestController
@RequestMapping("/handbook")
@Slf4j
public class RegionController {

    private final RegionService regionService;

    /**
     Конструктор контроллера регионов.
     @param regionService Сервис для работы с регионами.
     */
    @Autowired
    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    /**
     Получить список всех регионов.
     @return Список всех регионов.
     */
    @GetMapping
    public ResponseEntity<List<Region>> getAll() {
        List<Region> regions = regionService.getAll();
        log.info("GET /handbook - Успешно получены все регионы");
        return ResponseEntity.ok(regions);
    }

    /**
     Получить регион по идентификатору.
     @param id Идентификатор региона.
     @return Регион с указанным идентификатором или сообщение о его отсутствии.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Region> getById(@PathVariable Long id) {
        Region region = regionService.getById(id);
        if (region != null) {
            log.info("GET /handbook/{} - Успешно получен регион с идентификатором {}", id, id);
            return ResponseEntity.ok(region);
        } else {
            log.error("GET /handbook/{} - Регион с идентификатором {} не найден", id, id);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     Добавить новый регион.
     @param region Регион для добавления.
     @return Ответ о статусе добавления региона.
     */
    @PostMapping("/")
    public ResponseEntity<Void> insert(@RequestBody Region region) {
        try {
            regionService.insert(region);
            log.info("POST /handbook - Успешно добавлен регион с идентификатором {}", region.getId());
            return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch (DataAccessException e) {
            // Обрабатываем ошибку, например, логируем ее
            log.error("Не удалось добавить регион", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     Обновить информацию о регионе.
     @param id     Идентификатор региона.
     @param region Регион с обновленной информацией.
     @return Ответ о статусе обновления региона.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Region region) {
        if (regionService.existsById(id)) {
            region.setId(id);
            regionService.update(region);
            log.info("PUT /handbook - Успешно обновлен регион с идентификатором {}", region.getId());
            return ResponseEntity.ok().build();
        } else {
            log.error("PUT /handbook/{} - Регион с идентификатором {} не найден", id, id);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     Удалить регион по идентификатору.
     @param id Идентификатор региона.
     @return Ответ о статусе удаления региона.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>  deleteById(@PathVariable Long id) {
        if (regionService.existsById(id)) {
            regionService.deleteById(id);
            log.info("DELETE /handbook/{} - Успешно удален регион с идентификатором {}", id, id);
            return ResponseEntity.noContent().build();
        } else {
            log.error("DELETE /handbook/{} - Регион с идентификатором {} не найден", id, id);
            return ResponseEntity.notFound().build();
        }
    }
}
