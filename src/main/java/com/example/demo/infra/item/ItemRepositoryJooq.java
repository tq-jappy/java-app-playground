package com.example.demo.infra.item;

import com.example.demo.jooq.tables.records.ItemsRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.jooq.Tables.*;

@RequiredArgsConstructor
@Repository
public class ItemRepositoryJooq {

    private final DSLContext create;

    public List<Item> findAll() {
        Result<ItemsRecord> result = create.selectFrom(ITEMS)
                .limit(3)
                .fetch();

        return result.stream()
                .map(record -> {
                    Item item = new Item();
                    item.setId(Long.valueOf(record.getId()));
                    item.setName(record.getName());

                    return item;
                })
                .collect(Collectors.toList());
    }
}
