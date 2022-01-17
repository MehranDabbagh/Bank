package Repositories;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface CRUD<T> {
    String create(T t) throws SQLException;
    T readById(String id) throws SQLException;
    List<T> readAll() throws SQLException;
    Integer update(T t) throws SQLException;
    Integer delete(String id) throws SQLException;

}
