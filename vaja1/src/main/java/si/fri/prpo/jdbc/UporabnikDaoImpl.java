package si.fri.prpo.jdbc;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UporabnikDaoImpl implements BaseDao {
    private static              UporabnikDaoImpl instance;
    private static final        Logger           log = Logger.getLogger(UporabnikDaoImpl.class.getName());

    private Connection connection;

    public static UporabnikDaoImpl getInstance() {
        if(instance == null ){
            instance = new UporabnikDaoImpl();
        }

        return instance;
    }

    public UporabnikDaoImpl(){
        connection = getConnection();
    }


    private Uporabnik getUporabnikFromRS(ResultSet rs) throws SQLException {

        int id = rs.getInt("id");
        String ime = rs.getString("ime");
        String priimek = rs.getString("priimek");
        return new Uporabnik(id ,ime, priimek);
    
    }

    @Override
    public Connection getConnection(){
        try{
            InitialContext initCtx = new InitialContext();
            DataSource ds = (DataSource) initCtx.lookup("jdbc/SimpleJdbcDS");
            return ds.getConnection();
        } catch(Exception e){
            log.severe("Cannot get connection: "  + e.toString());
        }
        return null;
    }

    @Override
    public Entiteta vrni(int id){
        PreparedStatement ps = null;

        try{
            String query = "SELECT * FROM uporabnik WHERE id = ?;";
            ps = connection.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return getUporabnikFromRS(rs);
            }else{
                log.info("Uporabnik ne obstaja");
            }
        }catch(Exception e){
            log.severe(e.toString());
        } finally {
            if(ps != null){
                try{
                    ps.close();   
                }catch (SQLException e){
                    log.severe(e.toString());
                }
            }   
        }
        return null;
    }

    @Override
    public void odstrani(int id){
        PreparedStatement ps = null;

        try{
            String query = "DELETE FROM uporabnik WHERE id = ?;";
            ps = connection.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                getUporabnikFromRS(rs);
            }else{
                log.info("Uporabnik ne obstaja");
            }
        }catch(Exception e){
            log.severe(e.toString());
        } finally {
            if(ps != null){
                try{
                    ps.close();   
                }catch (SQLException e){
                    log.severe(e.toString());
                }
            }   
        }
    }

    @Override
    public void vstavi(Entiteta ent){
        PreparedStatement ps = null;

        try{
            String query = "INSERT INTO uporabnik(id,ime,priimek) VALUES(?,?,?);";
            ps = connection.prepareStatement(query);
            ps.setInt(1,ent.getId());
            ps.setString(2, ((Uporabnik)ent).getIme());
            ps.setString(3, ((Uporabnik)ent).getPriimek());
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                getUporabnikFromRS(rs);
            }else{
                log.info("Uporabnik ne obstaja");
            }
        }catch(Exception e){
            log.severe(e.toString());
        } finally {
            if(ps != null){
                try{
                    ps.close();   
                }catch (SQLException e){
                    log.severe(e.toString());
                }
            }   
        }
    }


    @Override
    public void posodobi(Entiteta ent){
        PreparedStatement ps = null;

        try{
            String query = "UPDATE uporabnik SET ime = ?, priimek = ? WHERE id = ?;";
            ps = connection.prepareStatement(query);
            ps.setString(1, ((Uporabnik)ent).getIme());
            ps.setString(2, ((Uporabnik)ent).getPriimek());
            ps.setInt(3, ent.getId());
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                getUporabnikFromRS(rs);
            }else{
                log.info("Uporabnik ne obstaja");
            }
        }catch(Exception e){
            log.severe(e.toString());
        } finally {
            if(ps != null){
                try{
                    ps.close();   
                }catch (SQLException e){
                    log.severe(e.toString());
                }
            }   
        }
    }

    @Override
    public List<Entiteta> vrniVse(){

        List<Entiteta> list = new ArrayList();
        Statement ps = null;
        try{
            ps = connection.createStatement();
            String query = "SELECT * FROM uporabnik;";
            ResultSet rs = ps.executeQuery(query);
            
            while(rs.next()) {
                list.add(getUporabnikFromRS(rs));
            }
        }catch(SQLException e){
            log.severe(e.toString());
        }finally{
            if(ps != null){
                try{
                    ps.close();   
                }catch (SQLException e){
                    log.severe(e.toString());
                }
            }   
        }
        return list;
    }
}
