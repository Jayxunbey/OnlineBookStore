package uz.pdp.online.dao;

public interface CrudDao<T,I> {

    /**

     */
    T save(T t);
    /**
     returns a saved user if added to Database successfully otherwise returns null
      */
    T getById(I bookId);

    T update(T t);

    boolean delete(I id);


}
