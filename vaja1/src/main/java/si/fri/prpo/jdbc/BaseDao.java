package si.fri.prpo.jdbc;

import java.util.List;

public interface BaseDao {
    Connection getConnection();
    Entiteta vrni(int id);
    void vstavi(Entiteta ent);
    void odstrani(int id);
    void posodobi(Entiteta ent);
    List<Entiteta> vrniVse();
}