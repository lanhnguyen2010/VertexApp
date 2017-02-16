package dao;

import domain.Entity;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.AsyncSQLClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PostgreSQLMapper<R extends Entity> extends Mapper<R, String> {

    private static final String SELECT_FROM = "SELECT * FROM ";
    private AsyncSQLClient client;
    private String table;

    public PostgreSQLMapper(AsyncSQLClient client, String table) {
        this.client = client;
        this.table = table;
    }

    @Override
    protected R newRecord(JsonObject json){
        return null;

    }

    @Override
    public void find(String queryWhere, Handler<AsyncResult<List<R>>> callback) {
        client.getConnection(checkConn -> {
            if (checkConn.failed()) {
                callback.handle(Future.failedFuture(checkConn.cause()));
                
            } else {
                checkConn.result().query(SELECT_FROM + table + " " + queryWhere, res -> {
                    if (res.failed()) {
                        callback.handle(Future.failedFuture(res.cause()));
                    } else {
                        List<R> result = new ArrayList<>();

                        result.addAll(res.result().getRows()
                                .stream()
                                .map(this::newRecord)
                                .collect(Collectors.toList()));
                        callback.handle(Future.succeededFuture(result));
                    }

                });
            }
        });

    }

    @Override
    public void findOne(String queryWhere, JsonArray params, Handler<AsyncResult<R>> callback) {


    }

    @Override
    public void findAll(Handler<AsyncResult<List<R>>> callback) {
        find("", callback);

    }

    @Override
    public void update(String query, R record, Handler<AsyncResult<Void>> callback) {

    }

    @Override
    public void remove(String query, Handler<AsyncResult<Void>> callback) {

    }

    @Override
    public void save(R record, Handler<AsyncResult<Integer>> callback) {

    }

    @Override
    public void findById(int id, Handler<AsyncResult<R>> callback) {
        client.getConnection(checkConn -> {
            if (checkConn.failed()) {
                callback.handle(Future.failedFuture(checkConn.cause()));
            } else {
                checkConn.result().queryWithParams(SELECT_FROM + table + " where id = ?",
                        new JsonArray().add(id), res -> {
                    if (res.failed() || res.result().getRows().size() <= 0) {
                        System.out.println("fail");
                        callback.handle(Future.failedFuture(res.cause()));
                    } else {
                        R result = newRecord(res.result().getRows().get(0));

                        callback.handle(Future.succeededFuture(result));
                    }

                });
            }
        });

    }

    @Override
    public void update(R record, Handler<AsyncResult<Void>> callback) {

    }

    @Override
    public void remove(R record, Handler<AsyncResult<Void>> callback) {

    }

    @Override
    public void count(String query, Handler<AsyncResult<Long>> callback) {

    }

    @Override
    public void count(Handler<AsyncResult<Long>> callback) {

    }

    @Override
    public void truncate(Handler<AsyncResult<Void>> callback) {

    }
}
