package si.fri.prpo.vaja1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.lang.*;
import java.util.*;
import com.kumuluz.ee.configuration.utils.ConfigurationUtil;

import si.fri.prpo.jdbc.*;

@WebServlet("/servlet")
public class Vaja1Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter in = resp.getWriter();
        Optional<String> microserviceName = ConfigurationUtil.getInstance().get("kumulizee.name");

        BaseDao uporabnikDao = UporabnikDaoImpl.getInstance();
        Uporabnik uporabnik = new Uporabnik((int)Math.round(Math.random()*5000),"Blake","Blakerson");

        in.append("Nov uporabnik...");
        uporabnikDao.vstavi(uporabnik);
        uporabnik.setIme("Gazda");
        uporabnikDao.posodobi(uporabnik);
        uporabnikDao.odstrani(uporabnik.getId());
        in.append("\n\n");

        in.append("Seznam\n");
        List<Entiteta> uporabniki = uporabnikDao.vrniVse();
        uporabniki.stream().forEach(u -> in.append(u.toString() + "\n"));
    }
}