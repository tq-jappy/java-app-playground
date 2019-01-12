package com.example.demo.controller;

import com.example.demo.infra.item.Item;
import com.example.demo.infra.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("api/jpa/items")
@RequiredArgsConstructor
public class JpaItemsController {

    private final ItemRepository itemRepository;

    @GetMapping()
    public List<Item> getItems() {
        Iterable<Item> items = itemRepository.findAll();

        return StreamSupport.stream(items.spliterator(), false)
                .collect(Collectors.toList());
    }
}
