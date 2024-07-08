package com.cafe.inventory.controller;

import com.cafe.inventory.domain.entities.InventoryJpa;
import com.cafe.inventory.domain.dto.Inventory;
import com.cafe.inventory.service.InventoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Inventory")
@RequestMapping("/api/inventory")
@AllArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/allInventory")
    public ResponseEntity<?> getAll()
    {
        return ResponseEntity.ok(inventoryService.getAll());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id)
    {
        return ResponseEntity.ok(inventoryService.findById(id));
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody Inventory inventory)
    {
        InventoryJpa inventoryJpaInserted = inventoryService.create(inventory);
        if(inventoryJpaInserted == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not Inserted");
        return ResponseEntity.ok(inventoryJpaInserted);
    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Inventory inventory)
    {
        InventoryJpa inventoryJpaUpdated = inventoryService.update(inventory);
        if(inventoryJpaUpdated == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not Updated");
        return ResponseEntity.ok(inventoryJpaUpdated);
    }

    @DeleteMapping("/delete/{id}")
    public  boolean delete(@PathVariable Long id)
    {
        inventoryService.delete(id);
        return true;
    }
}
