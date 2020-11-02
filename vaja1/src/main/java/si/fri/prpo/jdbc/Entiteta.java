package si.fri.prpo.jdbc;

import java.io.Serializable;

public abstract class Entiteta implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;

    public Integer getId(){ return id; }
    public void setId (final Integer id) { this.id = id; }

    public Entiteta(int id){
        this.id = id;
    }
    
}