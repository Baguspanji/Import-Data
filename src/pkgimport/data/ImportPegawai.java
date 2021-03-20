package pkgimport.data;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ImportPegawai extends SwingWorker<Void, Void> {

    private String lokasi, datanya, SQL_Insert;
    private String[] DataToInsert;
    private int i, numRow;

    private Connection con;
    private Statement s;

    public ImportPegawai(String fileExcel) {
        this.lokasi = fileExcel;
    }

    @Override
    protected Void doInBackground() throws Exception {
        try {
            datanya = "";
            con = KoneksiDB.getKoneksi();

            FileInputStream fis = new FileInputStream(new File(lokasi));
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);
            numRow = sheet.getPhysicalNumberOfRows();
            Iterator<Row> rowIter = sheet.iterator();

            /* proses membaca file excel */
            while (rowIter.hasNext()) {
                final Row r = rowIter.next();
                Iterator<Cell> cellIter = r.cellIterator();
                while (cellIter.hasNext()) {
                    Cell c = cellIter.next();
                    switch (c.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            c.setCellType(Cell.CELL_TYPE_STRING);
                            datanya += c.getStringCellValue().toString() + "#";
                            break;
                        case Cell.CELL_TYPE_STRING:
                            datanya += c.getStringCellValue().toString() + "#";
                            break;
                    }
                }

                /* Proses Insert ke database */
                DataToInsert = datanya.split("#");
                SQL_Insert = "";
                SQL_Insert = "INSERT INTO tb_pegawai (nik, nama, bagian) VALUES (";
                    SQL_Insert += "'" + DataToInsert[0].trim() + "',";
                    SQL_Insert += "'" + DataToInsert[1].trim() + "',";
                    SQL_Insert += "'" + DataToInsert[2].trim() + "'";
                SQL_Insert += ");";

                s = con.createStatement();
                s.execute(SQL_Insert);

                System.out.println(SQL_Insert);
                datanya = "";
                SQL_Insert = "";
            }

        } catch (Exception err) {
            err.printStackTrace();
        }
        return null;
    }

    protected void done() {
        JOptionPane.showMessageDialog(null, "Proses Selesai : " + numRow + " Row", "IMPORT", JOptionPane.INFORMATION_MESSAGE);
    }

}
