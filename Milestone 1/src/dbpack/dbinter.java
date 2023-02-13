package dbpack;
/*jdbc:postgresql://localhost:5432/postgres*/
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface dbinter {
    public void connect();
    public void disconnect();
    public void execute(String query);
    public ResultSet result(String query) ;
}
