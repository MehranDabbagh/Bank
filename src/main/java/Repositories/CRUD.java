package Repositories;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface CRUD<T> {
    String create(T t) ;
    T readById(String id) ;
    List<T> readAll() ;
    Integer update(T t) ;
    Integer delete(String id) ;

}
