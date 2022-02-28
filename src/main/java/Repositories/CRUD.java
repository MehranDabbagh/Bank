package Repositories;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface CRUD<T,I> {
    I create(T t) ;
    T readById(I id) ;
    List<T> readAll() ;
    Integer update(T t) ;
    Integer delete(I id) ;

}
