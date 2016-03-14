package dao;

import domain.Entity;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import java.util.List;

/**
 * Created by lanhnguyen on 07/03/2016.
 */
public abstract class Mapper <R extends Entity,Q> {

    protected abstract R newRecord(final JsonObject json);

    protected R newRecord() {
        return newRecord(new JsonObject());
    }

    public abstract void find(final Q queryWhere, final Handler<AsyncResult<List<R>>> callback);

    public abstract void findOne(final Q query, final JsonArray params, final Handler<AsyncResult<R>> callback);

    public abstract void findAll(final Handler<AsyncResult<List<R>>> callback);

    // Generic update

    public abstract void update(final Q query, final R record, final Handler<AsyncResult<Void>> callback);

    // Generic remove

    public abstract void remove(final Q query, final Handler<AsyncResult<Void>> callback);

    // Generic CRUD

    public abstract void save(final R record, final Handler<AsyncResult<Integer>> callback);

    public abstract void findById(int id, final Handler<AsyncResult<R>> callback);

    public abstract void update(final R record, final Handler<AsyncResult<Void>> callback);

    public abstract void remove(final R record, final Handler<AsyncResult<Void>> callback);

    // Generic utils

    public abstract void count(final Q query, final Handler<AsyncResult<Long>> callback);

    public abstract void count(final Handler<AsyncResult<Long>> callback);

    public abstract void truncate(final Handler<AsyncResult<Void>> callback);


}
