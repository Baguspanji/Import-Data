/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgimport.data;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.joda.time.DateTime;
import org.joda.time.Weeks;

/**
 *
 * @author JuruKetik
 */
public class SimpleDate {

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
        Date date1 = format.parse("01-02-2021 14:13:12.0");
        Date date2 = format.parse("01-03-2021 12:13:12.0");

        // timestamp to time
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date1);

        // string to time
//        Date d1 = dateFormat.parse(strDate);
//        Date d2 = dateFormat.parse("14:00:00");
//        Time ppstime = new Time(d1.getTime());
//        DateTime dateTime1 = new DateTime(date1);
//        DateTime dateTime2 = new DateTime(date2);
//
//        int weeks = Weeks.weeksBetween(dateTime1, dateTime2).getWeeks();
//        long diff = date2.getTime() - date1.getTime();
//        System.out.println("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
            System.out.println(strDate);
        
    }
}
