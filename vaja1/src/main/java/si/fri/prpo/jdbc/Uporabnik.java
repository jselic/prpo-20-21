package si.fri.prpo.jdbc;

public class Uporabnik extends Entiteta{
    private static final long serialVersionUID = 1L;

    private String ime;
    private String priimek;

    public Uporabnik(int id, String ime, String priimek) {
        super(id);
        this.ime = ime;
        this.priimek = priimek;
    }

    public String getIme(){ return ime;}
    public String getPriimek(){ return priimek;}
    public void setIme(String ime){ this.ime = ime;}
    public void setPriimek(String priimek){ this.priimek = priimek;}

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("ID: ");
        sb.append(Integer.toString(super.getId()));
        sb.append("Ime: ");
        sb.append(this.ime);
        sb.append("Priimek: ");
        sb.append(this.priimek);

        return sb.toString();
    }
}