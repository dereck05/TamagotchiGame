/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

/**
 *
 * @author derec
 */
public class Vista extends javax.swing.JFrame {

    /**
     * Creates new form Vista
     */
    public Vista() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnComer = new javax.swing.JButton();
        txtComer = new javax.swing.JTextField();
        btnEjercicio = new javax.swing.JButton();
        txtEjercicio = new javax.swing.JTextField();
        btnEnfermar = new javax.swing.JButton();
        txtEnfermedad = new javax.swing.JTextField();
        btnEstrategia = new javax.swing.JButton();
        btnHabilidad = new javax.swing.JButton();
        btnMedicina = new javax.swing.JButton();
        txtHabilidad = new javax.swing.JTextField();
        txtEstrategia = new javax.swing.JTextField();
        txtMedicina = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnComer.setText("Comer");
        btnComer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComerActionPerformed(evt);
            }
        });

        btnEjercicio.setText("Ejercitarse");
        btnEjercicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEjercicioActionPerformed(evt);
            }
        });

        txtEjercicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEjercicioActionPerformed(evt);
            }
        });

        btnEnfermar.setText("Enfermarse");
        btnEnfermar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnfermarActionPerformed(evt);
            }
        });

        txtEnfermedad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEnfermedadActionPerformed(evt);
            }
        });

        btnEstrategia.setText("Estrategia");
        btnEstrategia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstrategiaActionPerformed(evt);
            }
        });

        btnHabilidad.setText("Habilidad");
        btnHabilidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHabilidadActionPerformed(evt);
            }
        });

        btnMedicina.setText("Medicarse");
        btnMedicina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMedicinaActionPerformed(evt);
            }
        });

        txtHabilidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHabilidadActionPerformed(evt);
            }
        });

        txtEstrategia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEstrategiaActionPerformed(evt);
            }
        });

        txtMedicina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMedicinaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEjercicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnComer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEnfermar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEstrategia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHabilidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMedicina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtComer, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                    .addComponent(txtEjercicio)
                    .addComponent(txtEnfermedad)
                    .addComponent(txtHabilidad)
                    .addComponent(txtEstrategia, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMedicina))
                .addContainerGap(367, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnComer)
                    .addComponent(txtComer))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnEjercicio)
                    .addComponent(txtEjercicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEnfermar)
                    .addComponent(txtEnfermedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEstrategia)
                    .addComponent(txtEstrategia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHabilidad)
                    .addComponent(txtHabilidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMedicina)
                    .addComponent(txtMedicina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(151, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnComerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnComerActionPerformed

    private void btnEjercicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEjercicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEjercicioActionPerformed

    private void txtEjercicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEjercicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEjercicioActionPerformed

    private void btnEnfermarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnfermarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEnfermarActionPerformed

    private void txtEnfermedadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEnfermedadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEnfermedadActionPerformed

    private void btnEstrategiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstrategiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEstrategiaActionPerformed

    private void btnHabilidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHabilidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHabilidadActionPerformed

    private void btnMedicinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMedicinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMedicinaActionPerformed

    private void txtHabilidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHabilidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHabilidadActionPerformed

    private void txtEstrategiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstrategiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstrategiaActionPerformed

    private void txtMedicinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMedicinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMedicinaActionPerformed

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
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnComer;
    public javax.swing.JButton btnEjercicio;
    public javax.swing.JButton btnEnfermar;
    public javax.swing.JButton btnEstrategia;
    public javax.swing.JButton btnHabilidad;
    public javax.swing.JButton btnMedicina;
    public javax.swing.JTextField txtComer;
    public javax.swing.JTextField txtEjercicio;
    public javax.swing.JTextField txtEnfermedad;
    public javax.swing.JTextField txtEstrategia;
    public javax.swing.JTextField txtHabilidad;
    public javax.swing.JTextField txtMedicina;
    // End of variables declaration//GEN-END:variables
}