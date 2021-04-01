package pkgimport.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import models.Pegawai_model;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter {

    static String fileDictName = "";

    private Connection con;
    private Statement s;
    private ResultSet res;
    private String sql;
    private Pegawai_model modelPegawai = new Pegawai_model();

    public void exportAbsen(String dateFrom, String dateTo) throws FileNotFoundException, IOException, SQLException {

        con = KoneksiDB.getKoneksi();
        s = con.createStatement();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save file"); //name for chooser
        FileFilter filter = new FileNameExtensionFilter("Excel file", "xls", "xlsx"); //filter to show only that
        fileChooser.setAcceptAllFileFilterUsed(false); //to show or not all other files
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setFileFilter(filter);
        fileChooser.setSelectedFile(new File(fileDictName)); //when you want to show the name of file into the chooser
        fileChooser.setVisible(true);

        int result = fileChooser.showSaveDialog(fileChooser);
        if (result == JFileChooser.APPROVE_OPTION) {
            fileDictName = fileChooser.getSelectedFile().getAbsolutePath();
        } else {
            return;
        }

        File file = new File(fileDictName);
        if (file.exists() == false) {
            //Blank workbook
            XSSFWorkbook workbook = new XSSFWorkbook();

            //Create a blank sheet
            XSSFSheet sheet = workbook.createSheet("Employee Data");

            try {
                int no = 1;
                int urut = 2;
                int hari = 0;
                double uang = 0;
                String total = "";

                sql = modelPegawai.getUang(dateFrom, dateTo);
                res = s.executeQuery(sql);

                //This data needs to be written (Object[])
                Map<String, Object[]> data = new TreeMap<String, Object[]>();
                data.put("1", new Object[]{"No", "NIK", "Nama", "Bagian", "Esl", "Hari", "Uang"});
                while (res.next()) {
                    hari = modelPegawai.getUangNik(dateFrom, dateTo, res.getString(2));

                    int esl = modelPegawai.Romawi(res.getString(5).toUpperCase());

                    if (esl == 1) {
                        uang = 15000;
                    } else if (esl == 2) {
                        uang = 14000;
                    } else if (esl == 3) {
                        uang = 13000;
                    } else if (esl == 4) {
                        uang = 11000;
                    } else if (esl == 5) {
                        uang = 10000;
                    } else if (esl == 6) {
                        uang = 9000;
                    }

                    total = modelPegawai.toRupiah(uang, hari);
//                System.out.println(total);

                    String uruts = String.valueOf(urut);
                    data.put(uruts, new Object[]{no++, res.getString(2), res.getString(3), res.getString(4), res.getString(5), hari, total});
                    urut++;
                }

                //Iterate over data and write to sheet
                Set<String> keyset = data.keySet();
                int rownum = 0;
                for (String key : keyset) {
                    Row row = sheet.createRow(rownum++);
                    Object[] objArr = data.get(key);
                    int cellnum = 0;
                    for (Object obj : objArr) {
                        Cell cell = row.createCell(cellnum++);
                        if (obj instanceof String) {
                            cell.setCellValue((String) obj);
                        } else if (obj instanceof Integer) {
                            cell.setCellValue((Integer) obj);
                        }
                    }
                }

                try (
                        //Write the workbook in file system
                        FileOutputStream out = new FileOutputStream(file + ".xlsx")) {
                    workbook.write(out);
                    out.close();

                    JOptionPane.showMessageDialog(null, "Data berhasil disimpan!!");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            // Sheet already exists
            System.out.println("File already exist");
            JOptionPane.showMessageDialog(null, "Nama File sudah ada, coba nama lain!!");
        }
    }

}
