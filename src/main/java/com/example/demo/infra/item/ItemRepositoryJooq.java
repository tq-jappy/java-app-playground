package com.example.demo.infra.item;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.demo.jooq.Tables.*;

@RequiredArgsConstructor
@Repository
public class ItemRepositoryJooq {

    private final DSLContext create;

    public List<Item> findAll() {
        return create.selectFrom(ITEMS)
                .limit(3)
                .fetchInto(Item.class);
    }
}
