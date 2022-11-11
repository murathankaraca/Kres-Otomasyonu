package meltem.services.data_access;

import java.sql.SQLException;
import java.util.List;

public interface IRepository<T> {
    public T fetchById(int id) throws SQLException;
    public List<T> fetchAll() throws SQLException;
    public void Add(T entity) throws SQLException;
    public void UpdateById(T entity, int id) throws SQLException;
    public void Delete(int id) throws SQLException;
}