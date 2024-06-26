package com.cafe.inventory.mapper;

import com.cafe.inventory.domain.dto.Inventory;
import com.cafe.inventory.domain.entities.InventoryJpa;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class InventoryConverter {
    private ModelMapper modelMapper;
    public InventoryConverter(ModelMapper modelMapper)
    {
        this.modelMapper = modelMapper;
        //TypeMap<Inventory, InventoryJpa> type = this.modelMapper.createTypeMap(Inventory.class, InventoryJpa.class);

    }
    public Inventory convert(InventoryJpa inventoryJpa)
    {
        Inventory inventory = modelMapper.map(inventoryJpa,Inventory.class);
        return inventory;
    }
    public InventoryJpa convert(Inventory inventory)
    {
        InventoryJpa inventoryJpa = modelMapper.map(inventory,InventoryJpa.class);
        return inventoryJpa;
    }
}
