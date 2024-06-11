package com.cafe.service;

import com.cafe.domain.dto.Inventory;
import com.cafe.domain.entities.InventoryJpa;
import com.cafe.exceptionHandler.RecordNotComplete;
import com.cafe.exceptionHandler.RecordNotFoundException;
import com.cafe.mapper.InventoryConverter;
import com.cafe.repositories.InventoryRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    private final InventoryRepo inventoryRepo;
    private final InventoryConverter inventoryConverter;
    Logger logger = LoggerFactory.getLogger(InventoryService.class);

    public InventoryService(InventoryRepo inventoryRepo, InventoryConverter inventoryConverter)
    {
        this.inventoryRepo = inventoryRepo;
        this.inventoryConverter = inventoryConverter;
    }
    public List<Inventory> getAll()
    {
        List<InventoryJpa> inventoryJpas = inventoryRepo.findAll();
        List<Inventory> inventories = new ArrayList<>();
        for (InventoryJpa inventoryJpa:inventoryJpas) {
            inventories.add(inventoryConverter.convert(inventoryJpa));
        }
        return inventories;
    }

    public Inventory findById(Long Id)
    {
        Inventory inventory = inventoryConverter.convert(inventoryRepo.findById(Id).get());
        return inventory;
    }

    public InventoryJpa create(Inventory inventory)
    {
        try {
            InventoryJpa inventoryJpa = inventoryConverter.convert(inventory);
            InventoryJpa inventoryJpaInserted = inventoryRepo.save(inventoryJpa);
            return inventoryJpaInserted;
        }catch (Exception ex)
        {
            logger.error(ex.getMessage());
            throw new RecordNotComplete("This Record Is Not Inserted");
        }
    }

    public InventoryJpa update(Inventory inventory)
    {
        try{
            Optional<InventoryJpa> inventoryJpaFind = inventoryRepo.findById(inventory.getId());
            if(inventoryJpaFind.isPresent()) {
                InventoryJpa inventoryJpa = inventoryConverter.convert(inventory);
                InventoryJpa inventoryJpaUpdated = inventoryRepo.save(inventoryJpa);
                return  inventoryJpaUpdated;
            }
            throw new RecordNotFoundException("This Record Is Not Found Of Id = "+inventory.getId());
        }catch (Exception ex)
        {
            logger.error(ex.getMessage());
            throw new RecordNotComplete("This Record Is Not Updated");
        }
    }

    public void delete(Long id)
    {
        inventoryRepo.deleteById(id);
    }
}
