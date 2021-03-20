package pkgimport.data;

import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

public class FrameImport {

    private JFrame frame;
    private JFileChooser jfc;
    private File file;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrameImport window = new FrameImport();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public FrameImport() {
        initLookAndFeel();
        initialize();
    }

    private void initLookAndFeel() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception err) {

        }
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setBounds(100, 100, 405, 181);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Import Excel");
        frame.getContentPane().setLayout(null);

        JButton btnImport = new JButton("Import");
        btnImport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                jfc = new JFileChooser();
                jfc.setDialogTitle("Pilih File Excel");
                if (jfc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                    file = jfc.getSelectedFile();
                    new ImportPegawai(file.getAbsolutePath()).execute();
                }
            }
        });
        btnImport.setBounds(123, 52, 138, 43);
        btnImport.setCursor(new Cursor(Cursor.HAND_CURSOR));
        frame.getContentPane().add(btnImport);
    }
}
