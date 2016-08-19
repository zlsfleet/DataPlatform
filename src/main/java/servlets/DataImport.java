package servlets;

import beans.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Kris on 2016/7/5.
 */
public class DataImport {

    public static void main(String[] args) {
        importData();
    }

    public static void importData() {
        String urlString = "http://www.fuhuantech.com/data.json";

        URL url;

        BufferedInputStream bis;
        StringBuilder sb = new StringBuilder();

        Configuration conf = new Configuration();
        conf = conf.configure();
        ServiceRegistry sr = new ServiceRegistryBuilder().applySettings(conf
                .getProperties()).buildServiceRegistry();
        SessionFactory sf = conf.buildSessionFactory(sr);
        Session session = sf.openSession();

        try {

            url = new URL(urlString);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            InputStream inStream = conn.getInputStream();
            Reader reader = new InputStreamReader(inStream);
            int tempchar;
            while ((tempchar = reader.read()) != -1){
                //System.out.print((char)tempchar);
                sb.append((char)tempchar);
            }
            String str = sb.toString();
            //System.out.println(str);
            JSONArray json = JSONArray.fromObject(str);
            for (Object o : json){
                JSONObject jo = (JSONObject) o;
                // System.out.println(jo.get("Surveyed"));
                String date = jo.getString("Date");

                DevicesEntity devicesBean = new DevicesEntity();
                devicesBean.setDate(date);
                devicesBean.setInstalled(jo.getInt("Installed_device"));
                devicesBean.setTesting(jo.getInt("Testing"));
                devicesBean.setRepairing(jo.getInt("Repairing"));
                devicesBean.setWorking(jo.getInt("Working"));
                devicesBean.setTotalDevices(jo.getInt("Total_devices"));

                ExpressEntity expressBean = new ExpressEntity();
                Calendar c = Calendar.getInstance();
                c.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(date));
                c.set(Calendar.DATE, c.get(Calendar.DATE) - 1);
                String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());

                String hql = "from ExpressEntity where date = ?";

                Query query;
                query = session.createQuery(hql);
                query.setString(0, dayBefore);
                List<ExpressEntity> oldExpress = query.list();
                int oldRecipient = oldExpress.get(0).getRecipients();
                int oldPackages = oldExpress.get(0).getPackages();
                int oldCouriers = oldExpress.get(0).getCouriers();

                expressBean.setDate(date);
                expressBean.setRecipients(jo.getInt("Recipients"));
                expressBean.setRecipientsPlus(jo.getInt("Recipients") - oldRecipient);
                expressBean.setPackages(jo.getInt("Packages"));
                expressBean.setPackagesPlus(jo.getInt("Packages") - oldPackages);
                expressBean.setCouriers(jo.getInt("Couriers"));
                expressBean.setCouriersPlus(jo.getInt("Couriers") - oldCouriers);
                expressBean.setReturns(jo.getInt("Returns"));

                SpotsEntity spotsBean = new SpotsEntity();
                spotsBean.setDate(date);
                spotsBean.setSigned(jo.getInt("Signed"));
                spotsBean.setSurveyed(jo.getInt("Surveyed"));
                spotsBean.setWorked(jo.getInt("Worked"));
                spotsBean.setInstalled(jo.getInt("Installed"));
                spotsBean.setToRemove(jo.getInt("To_remove"));
                spotsBean.setTotalSpots(jo.getInt("Total_spots"));

                O2OEntity o2oBean = new O2OEntity();
                hql = "from O2OEntity where date = ?";

                query = session.createQuery(hql);
                query.setString(0, dayBefore);
                List<O2OEntity> oldO2O = query.list();
                int oldAppUsers = oldO2O.get(0).getAppUsers();
                int oldOrderedUsers = oldO2O.get(0).getOrderedUsers();
                int oldAppOrders = oldO2O.get(0).getAppOrders();
                o2oBean.setDate(date);
                o2oBean.setAppUsers(jo.getInt("App_users"));
                o2oBean.setAppUsersPlus(jo.getInt("App_users") - oldAppUsers);
                o2oBean.setOrderedUsers(jo.getInt("Ordered_users"));
                o2oBean.setOrderedUsersPlus(jo.getInt("Ordered_users") - oldOrderedUsers);
                o2oBean.setAppOrders(jo.getInt("App_orders"));
                o2oBean.setAppOrdersPlus(jo.getInt("App_orders") - oldAppOrders);
                o2oBean.setHomeVisits(jo.getInt("App_visitors"));

                ServiceEntity serviceBean = new ServiceEntity();
                serviceBean.setDate(date);
                serviceBean.setCallIn(jo.getInt("Call_in"));
                serviceBean.setCallOut(jo.getInt("Call_out"));
                serviceBean.setBlacklist(jo.getInt("Blacklist"));
                serviceBean.setToVerify(jo.getInt("To_verify"));

                // WechatEntity wechatBean = new WechatEntity();

                WorkOrderEntity workOrderBean = new WorkOrderEntity();
                workOrderBean.setDate(date);
                workOrderBean.setNewOrders(jo.getInt("New_orders"));
                workOrderBean.setFinishedOrders(jo.getInt("Finished_orders"));
                workOrderBean.setRemainingOrders(jo.getInt("Remaining_orders"));

                Transaction trans = session.beginTransaction();
                session.save(devicesBean);
                session.save(expressBean);
                session.save(spotsBean);
                session.save(o2oBean);
                session.save(serviceBean);
                session.save(workOrderBean);
                trans.commit();

                System.out.println("Successfully saved: " + date);

            }
            OutputStream outStream = conn.getOutputStream();
            OutputStreamWriter outputWriter = new OutputStreamWriter(outStream);
            BufferedWriter bufferWriter = new BufferedWriter(outputWriter);

            String line = "[]";
            bufferWriter.write(line, 0, line.length());
            bufferWriter.newLine();
            bufferWriter.flush();
            System.out.println("Cleared");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
