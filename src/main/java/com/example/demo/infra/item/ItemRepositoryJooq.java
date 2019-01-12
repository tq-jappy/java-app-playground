package com.example.demo.infra.item;

import com.example.demo.jooq.tables.records.ItemsRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.demo.jooq.Tables.*;

@RequiredArgsConstructor
@Repository
public class ItemRepositoryJooq {

    private final DSLContext create;

    public Optional<Item> findOne(Integer id) {
        Item item = create.selectFrom(ITEMS)
                .where(ITEMS.ID.eq(id))
                .fetchOneInto(Item.class);
        return Optional.ofNullable(item);
    }
    
    public List<Item> findAll() {
        return create.selectFrom(ITEMS)
                .limit(3)
                .fetchInto(Item.class);
    }

    public int create(Item item) {
        ItemsRecord record = create.newRecord(ITEMS, item);

        return create.executeInsert(record);
    }

    public int createWithName(String name) {
        return create.insertInto(ITEMS, ITEMS.NAME)
                .values(name)
                .execute();
    }

    public int merge(int id, String name) {
        return create.insertInto(ITEMS, ITEMS.ID, ITEMS.NAME)
                .values(id, name)
                .onDuplicateKeyUpdate()
                .set(ITEMS.NAME, name)
                .execute();
    }
}
