package interfaces;

import java.util.List;

public interface IFacade <T> {
    public T create(T classDTO);
    public T getById(long id);
    public long getCount();
    public List<T> getAll();
    public List<T> getSpecific(String one);
}
