package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import pkgimport.data.KoneksiDB;

public class Pegawai_model {

    int hari;

    public String getData() {
        return "SELECT * FROM tb_pegawai";
    }

    public String getByNik(String nik) {
        return "SELECT * FROM tb_pegawai WHERE nik = '" + nik + "' LIMIT 1";
    }

    public String getAbsen(String dateFrom, String dateTo) {
        return "SELECT `a`.`id_absen`, `p`.`nik`, `p`.`nama`, `p`.`bagian`, `a`.`tanggal` "
                + "FROM `tb_absen` AS `a` JOIN `tb_pegawai` AS `p` ON `a`.`nik` = `p`.`nik` "
                + "WHERE (DATE_FORMAT(tanggal,'%Y-%m-%d') BETWEEN '" + dateFrom + "' AND '" + dateTo + "') AND "
                + "`p`.`status` = 1 ORDER BY nik ASC";
    }

    public String getUang(String dateFrom, String dateTo) {
        return "SELECT `a`.`id_absen`, `p`.`nik`, `p`.`nama`, `p`.`bagian`, `p`.`esl`, `a`.`tanggal` "
                + "FROM `tb_absen` AS `a` JOIN `tb_pegawai` AS `p` ON `a`.`nik` = `p`.`nik` "
                + "WHERE (DATE_FORMAT(tanggal,'%Y-%m-%d') BETWEEN '" + dateFrom + "' AND '" + dateTo + "') AND "
                + "`p`.`status` = 1 GROUP BY nik";
    }

    public int getUangNik(String dateFrom, String dateTo, String nik) throws SQLException, ParseException {
        Connection con = KoneksiDB.getKoneksi();
        Statement s = con.createStatement();

        String sql = "SELECT * FROM `tb_absen` "
                + "WHERE (DATE_FORMAT(tanggal,'%Y-%m-%d') BETWEEN '" + dateFrom + "' AND '" + dateTo + "') AND "
                + "`nik` = '" + nik + "' GROUP BY DATE_FORMAT(tanggal,'%Y-%m-%d')";

        ResultSet res = s.executeQuery(sql);

        hari = 0;
        while (res.next()) {
            String tanggal = timestamToDate(res.getString(3));
            boolean count = getDateNik(tanggal, res.getString(2));
            if (count == true) {
                hari = hari + 1;
            }
//            System.out.println(res.getString(2) + " " + tanggal);
        }

//        System.out.println("Hari : " + hari);

        return hari;
    }

    public boolean getDateNik(String date, String nik) throws SQLException, ParseException {
        Connection con = KoneksiDB.getKoneksi();
        Statement s = con.createStatement();
        boolean hari = false;

        String sql = "SELECT * FROM `tb_absen` WHERE DATE_FORMAT(tanggal,'%Y-%m-%d') = '" + date + "' "
                + "AND `nik` = '" + nik + "' ORDER BY `tanggal` DESC LIMIT 1";

        ResultSet res = s.executeQuery(sql);
        while (res.next()) {
            Date absen = dateToTime(res.getString(3));
            Date siang = stringToTime("14:00:00");
            if (siang.getTime() < absen.getTime()) {
                hari = true;
            }
//            System.out.println(hari);
        }

        return hari;
    }

    public String setPegawaiAll(String nama, String bagian, String nik) {
        return "UPDATE tb_pegawai SET nama='" + nama + "', bagian='" + bagian + "' WHERE nik='" + nik + "'";
    }

    public String setPegawaiStatus(int status, String nik) {
        return "UPDATE tb_pegawai SET status='" + status + "' WHERE nik='" + nik + "'";
    }

    public Date dateToTime(String tanggal) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
        Date date = format.parse(tanggal);

        // time to string
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String strDate = dateFormat.format(date);

        return stringToTime(strDate);
    }

    public Date stringToTime(String jam) throws ParseException {

        // string to time
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date time = dateFormat.parse(jam);

        return time;
    }

    public String timestamToDate(String tanggal) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date dates = format.parse(tanggal);

        // timestamp to date
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(dates);

        // string to time
        Date date = dateFormat.parse(strDate);
//        System.out.println(tanggal +" to: "+strDate);

        return strDate;
    }

    public int Romawi(String romawi) {
        String Roman = romawi;
        int largo = Roman.length();
        char Roman2[] = new char[largo];
        int Roman3[] = new int[largo];

        for (int i = 0; i < largo; i++) {
            Roman2[i] = Roman.charAt(i);
        }

        for (int i = 0; i < largo; i++) {
            if (Roman2[i] == 'I') {
                Roman3[i] = 1;
            } else if (Roman2[i] == 'V') {
                Roman3[i] = 5;
                if (i > 0 && Roman2[i - 1] == 'I') { // check for IV
                    Roman3[i] = 4;
                    Roman3[i - 1] = 0;
                }
            } else if (Roman2[i] == 'X') {
                Roman3[i] = 10;
            } else if (Roman2[i] == 'L') {
                Roman3[i] = 50;
            } else if (Roman2[i] == 'C') {
                Roman3[i] = 100;
            } else if (Roman2[i] == 'M') {
                Roman3[i] = 1000;
            }
        }

        int total = 0;

        for (int m = 0; m < Roman3.length; m++) {
            total += Roman3[m];
        }

//        System.out.println("The Roman is equal to " + total);

        return total;
    }
    
    public String toRupiah(double uang, int hari){
        
        double harga = uang * hari;

        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);
//        System.out.printf("Harga Rupiah: %s %n", kursIndonesia.format(harga));
        
        return kursIndonesia.format(harga);
        
    }
}
