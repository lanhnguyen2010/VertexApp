package service;

import io.vertx.core.json.Json;
import io.vertx.ext.asyncsql.AsyncSQLClient;
import io.vertx.ext.web.RoutingContext;
import repository.UserRepository;

/**
 * @author lanhnguyen on 07/03/2016.
 */
public class UserService {

    private static final String USER_TABLE = "users";
    public static final String CONTENT_TYPE = "content-type";
    public static final String JSON_CONTENT_TYPE = "application/json; charset=utf-8";

    private UserRepository userRepository;

    public UserService(AsyncSQLClient postgreSQLClient){
        this.userRepository = new UserRepository(postgreSQLClient, USER_TABLE);
    }

    public void getUsers(RoutingContext routingContext){
        userRepository.findAll(callback -> {
            if (callback.succeeded()){
                routingContext.response().setStatusCode(200)
                        .putHeader(CONTENT_TYPE, JSON_CONTENT_TYPE)
                        .end(Json.encodePrettily(callback.result()));

            }else {
                routingContext.response().setStatusCode(404).end();
            }
        });

    }

    public void getById(RoutingContext routingContext){
        String id = routingContext.request().getParam("id");
        try {
            userRepository.findById(Integer.parseInt(id), callback -> {
                if (callback.succeeded()) {
                    routingContext.response().setStatusCode(200)
                            .putHeader(CONTENT_TYPE, JSON_CONTENT_TYPE)
                            .end(Json.encodePrettily(callback.result()));

                } else {
                    routingContext.response().setStatusCode(404).end();
                }
            });
        } catch (Exception e){
            routingContext.response().setStatusCode(404).setStatusMessage("Invalid ID").end();
        }

    }
}
