/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.pegawai;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import pkgimport.data.KoneksiDB;

/**
 *
 * @author bp
 */
public class Edit_pegawai extends javax.swing.JFrame {

    /**
     * Creates new form Edit_pegawai
     */
    private Connection con;
    private Statement s;
    private ResultSet rs;

    public Edit_pegawai() throws SQLException {
        initComponents();
        
        this.setUndecorated(true);

        txt_status.setEditable(false);
        txt_nik.setEditable(false);

        con = KoneksiDB.getKoneksi();
        s = con.createStatement();
    }

    public void setText(String nik) {
        try {
            String sql = "SELECT * FROM tb_pegawai WHERE nik = '" + nik + "'";
            rs = s.executeQuery(sql);

            while (rs.next()) {
                String status = "";
                if (rs.getInt("status") == 1) {
                    status = "aktif";
                } else {
                    status = "non-aktif";
                }
                txt_nik.setText(rs.getString("nik"));
                txt_nama.setText(rs.getString("nama"));
                txt_bagian.setText(rs.getString("bagian"));
                txt_status.setText(status);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btn_status = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_back = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txt_nik = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_nama = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_bagian = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_status = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Pegawai");

        jPanel2.setBackground(new java.awt.Color(195, 107, 7));

        btn_status.setBackground(new java.awt.Color(161, 32, 16));
        btn_status.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        btn_status.setText("STATUS");
        btn_status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_statusActionPerformed(evt);
            }
        });

        btn_update.setBackground(new java.awt.Color(237, 200, 11));
        btn_update.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        btn_update.setText("UPDATE");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_back.setBackground(new java.awt.Color(106, 111, 121));
        btn_back.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        btn_back.setText("BACK");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });

        jLabel2.setFont(jLabel2.getFont().deriveFont(jLabel2.getFont().getSize()+3f));
        jLabel2.setText("Nik");

        txt_nik.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N

        jLabel3.setFont(jLabel3.getFont().deriveFont(jLabel3.getFont().getSize()+3f));
        jLabel3.setText("Nama");

        txt_nama.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        txt_nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namaActionPerformed(evt);
            }
        });

        jLabel4.setFont(jLabel4.getFont().deriveFont(jLabel4.getFont().getSize()+3f));
        jLabel4.setText("Bagian");

        txt_bagian.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        txt_bagian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bagianActionPerformed(evt);
            }
        });

        jLabel5.setFont(jLabel5.getFont().deriveFont(jLabel5.getFont().getSize()+3f));
        jLabel5.setText("Status");

        txt_status.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        txt_status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_statusActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("EDIT PEGAWAI");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_back, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(btn_status))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_nik, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_nama, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txt_bagian, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_status, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))))
                .addContainerGap(40, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_nik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_bagian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_status)
                    .addComponent(btn_update)
                    .addComponent(btn_back))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(440, 342));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namaActionPerformed

    private void txt_bagianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bagianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bagianActionPerformed

    private void txt_statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_statusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_statusActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        String nik = txt_nik.getText();
        String nama = txt_nama.getText();
        String bagian = txt_bagian.getText();

        try {
            String query = "UPDATE tb_pegawai SET nama='" + nama + "', bagian='" + bagian + "' WHERE nik='" + nik + "'";

            s = con.createStatement();
            s.execute(query);

            Pegawai fa = new Pegawai();
            fa.setVisible(true);

            this.setVisible(false);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal" + e.getMessage());
        }
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_statusActionPerformed
        String nik = txt_nik.getText();

        try {
            String sql = "SELECT * FROM tb_pegawai WHERE nik = '" + nik + "'";
            rs = s.executeQuery(sql);

            while (rs.next()) {
                int status = 1;
                if (rs.getInt("status") == 1) {
                    status = 0;
                }

                String query = "UPDATE tb_pegawai SET status='" + status + "' WHERE nik='" + nik + "'";

                s = con.createStatement();
                s.execute(query);

                setText(nik);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btn_statusActionPerformed

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        try {
            Pegawai fa = new Pegawai();
            fa.setVisible(true);
            this.setVisible(false);
        } catch (SQLException ex) {
            Logger.getLogger(Edit_pegawai.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_backActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Edit_pegawai.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Edit_pegawai.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Edit_pegawai.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Edit_pegawai.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Edit_pegawai().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_status;
    private javax.swing.JButton btn_update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txt_bagian;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_nik;
    private javax.swing.JTextField txt_status;
    // End of variables declaration//GEN-END:variables
}
