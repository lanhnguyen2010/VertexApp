package cmd;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.asyncsql.AsyncSQLClient;
import io.vertx.ext.asyncsql.PostgreSQLClient;
import io.vertx.ext.web.Router;
import service.UserService;
import util.ConfigFactory;

/**
 * @author lanhnguyen on 01/03/2016.
 */
public class MyFirstVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {

        ConfigFactory.loadDBConfig();

        AsyncSQLClient postgreSQLClient = PostgreSQLClient.createShared(vertx, ConfigFactory.getDbConfig());
        UserService userService = new UserService(postgreSQLClient);

        Router router = Router.router(vertx);

        router.get("/api/users").handler(userService::getUsers);
        router.get("/api/users/:id").handler(userService::getById);

        vertx.createHttpServer().requestHandler(router::accept).listen(8000);
    }

}
