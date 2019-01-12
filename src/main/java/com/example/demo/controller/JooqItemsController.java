package com.example.demo.controller;

import com.example.demo.infra.item.Item;
import com.example.demo.infra.item.ItemRepositoryJooq;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/jooq/items")
@RequiredArgsConstructor
public class JooqItemsController {

    private final ItemRepositoryJooq itemRepositoryJooq;

    @GetMapping()
    public List<Item> getItems() {
        return itemRepositoryJooq.findAll();
    }

    @GetMapping("{id}")
    public Item getItemById(@PathVariable Integer id) {
        return itemRepositoryJooq.findOne(id)
                // TODO: use custom error handler
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }
}
