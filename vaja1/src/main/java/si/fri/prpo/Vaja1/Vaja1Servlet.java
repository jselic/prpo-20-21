package si.fri.prpo.vaja1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.module.Configuration;
import com.kumuluz.ee.configuration.utils.ConfigurationUtil;

import si.fri.prpo.jdbc.BaseDao;
import si.fri.prpo.jdbc.UporabnikDaoImpl;

@WebServlet("/servlet")
public class Vaja1Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter in = resp.getWriter();
        Optional<String> microserviceName = ConfigurationUtil.getInstance().get("kumulizee.name");

        BaseDao uporabnikDao = UporabnikDaoImpl.getInstance();
        Uporabnik uporabnik = new Uporabnik(222,"Blake","Blakerson");

        in.append("Nov uporabnik...");
        uporabnikDao.vstavi(uporabnik);
        writer.append("\n\n");

        writer.append("Seznam\n");
        List<Entiteta> uporabniki = uporabnikDao.vrniVse();
        uporabniki.stream().forEach(u -> in.append("\n"));
    }
}