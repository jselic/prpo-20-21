package si.fri.prpo.vaja1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.module.Configuration;
import com.kumuluz.ee.configuration.utils.ConfigurationUtil;

@WebServlet("/servlet")
public class Vaja1Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("hello");
        resp.getWriter().println(ConfigurationUtil.getInstance().get("kumuluzee.name"));
        resp.getWriter().println(ConfigurationUtil.getInstance().get("kumuluzee.version"));
        resp.getWriter().println(ConfigurationUtil.getInstance().get("kumuluzee.env.name"));

    }
}