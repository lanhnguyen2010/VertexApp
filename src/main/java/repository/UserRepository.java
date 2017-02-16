package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.PostgreSQLMapper;
import domain.User;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.AsyncSQLClient;
import java.io.IOException;

/**
 * author: lanhnguyen on 08/03/2016.
 */
public class UserRepository extends PostgreSQLMapper<User> {

    public UserRepository(AsyncSQLClient client, String table) {
        super(client, table);
    }
    @Override
    protected User newRecord(JsonObject json){
        ObjectMapper mapper = new ObjectMapper();
        User user = null;
        try {
            user =  mapper.readValue(json.toString().getBytes(), User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return user;
    }
}
