package servlets;

import net.sf.json.JSONArray;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Kris on 2016/5/18.
 */
public class DataServlet extends HttpServlet {
    public DataServlet() {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DataImport.importData();

        Configuration conf = new Configuration();
        conf = conf.configure();
        ServiceRegistry sr = new ServiceRegistryBuilder().applySettings(conf
                .getProperties()).buildServiceRegistry();
        SessionFactory sf = conf.buildSessionFactory(sr);
        Session session = sf.openSession();
        String type = request.getParameter("type");
        String hql;

        switch (type) {
            case "device":
                hql = "from DevicesEntity";
                break;
            case "express":
                hql = "from ExpressEntity";
                break;
            case "o2o":
                hql = "from O2OEntity";
                break;
            case "wechat":
                hql = "from WechatEntity";
                break;
            case "spot":
                hql = "from SpotsEntity";
                break;
            case "user":
                hql = "from UsersEntity";
                break;
            case "workorder":
                hql = "from WorkOrderEntity";
                break;
            case "service":
                hql = "from ServiceEntity";
                break;
            default:
                hql = "from DevicesEntity";
        }

        Query query;
        query = session.createQuery(hql);
        List list = query.list();

        response.setContentType("text/html; charset=utf-8");
        JSONArray jsonArray = JSONArray.fromObject(list);

        PrintWriter out = response.getWriter();
        out.println(jsonArray);
        out.flush();
        out.close();


    }
}
