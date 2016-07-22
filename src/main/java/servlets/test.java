package servlets;

import beans.ExpressEntity;
import net.sf.json.JSONArray;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Kris on 2016/7/22.
 */
public class test {
    public static void main(String args[]) {
        Configuration conf = new Configuration();
        conf = conf.configure();
        ServiceRegistry sr = new ServiceRegistryBuilder().applySettings(conf
                .getProperties()).buildServiceRegistry();
        SessionFactory sf = conf.buildSessionFactory(sr);
        Session session = sf.openSession();

        String date = "2016-06-01";
        Calendar c = Calendar.getInstance();
        Date d = null;
        try {
            d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(d);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);
        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        System.out.println(dayBefore);

        String hql = "from ExpressEntity where date = ?";

        Query query;
        query = session.createQuery(hql);
        query.setString(0, dayBefore);
        List<ExpressEntity> list = query.list();
        System.out.println(list.get(0).getCouriers());

        JSONArray jsonArray = JSONArray.fromObject(list);
    }
}
