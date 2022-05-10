package com.example.studyproject.repository.db;

import com.example.studyproject.repository.AccountRepository;
import com.example.studyproject.service.model.Account;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jooq.*;
import org.jooq.Record;
import org.springframework.stereotype.Component;


import java.util.Optional;
import java.util.UUID;

import static com.example.jooq.public_.tables.Account.ACCOUNT;
@Component
public class AccountRepositoryJooqPostgres implements AccountRepository {

    private final DSLContext context;

    public AccountRepositoryJooqPostgres(DSLContext context) {
        this.context = context;
    }


    @NotNull
    private RecordMapper<Record, Account> getRecordAccountRecordMapper() {
        return new RecordMapper<Record, Account>() {
            @Override
            public @Nullable Account map(Record record) {
                return null;
            }

            @Override
            public Account apply(Record record) {
                return RecordMapper.super.apply(record);
            }
        };
    }

    @Override
    public Account update(String accountId, Integer amount) {
        return null;
    }

    @Override
    public Account create() {
      return  context.insertInto(ACCOUNT,ACCOUNT.ID, ACCOUNT.VALUE)
                .values(UUID.randomUUID(),0l)
                .returningResult()
                .fetchOne().map(getRecordAccountRecordMapper());
    }


    @Override
    public Optional<Long> getBalance(String accountId) {
        Record1<Long> balance = context.select(ACCOUNT.VALUE)
                .from(ACCOUNT)
                .where(ACCOUNT.ID.equal(UUID.fromString(accountId)))
                .fetchAny();
        return Optional.ofNullable(balance.value1());
    }

    @Override
    public void deleteAll() {
        context.deleteFrom(ACCOUNT).execute();
    }

    @Override
    public Account create(UUID uuid, long value) {
        return  context.insertInto(ACCOUNT,ACCOUNT.ID, ACCOUNT.VALUE)
                .values(uuid,value)
                .returningResult()
                .fetchOne().map(getRecordAccountRecordMapper());
    }
}
