/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import classes.AreaConcurso;
import fachada.Fachada;
import fachada.IFachada;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RAFAELSANTIAGO
 */
public class ListarAreaConcurso extends javax.swing.JFrame {
  IFachada fachada = Fachada.obterInstancia();
    DefaultTableModel modelo = new DefaultTableModel();
    /**
     * Creates new form ListarAreaConcurso
     */
    public ListarAreaConcurso() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
     public void carregarListaAreaConcurso() {
        // AreaConcurso areaConcurso= new AreaConcurso();
         try {
             modelo = new DefaultTableModel();
            modelo.setColumnIdentifiers(new Object[]{"Id", "NomeArea","NomeConcurso", "Vagas"});
            ArrayList<AreaConcurso> areas = new ArrayList<AreaConcurso>();
            areas = (ArrayList<AreaConcurso>) fachada.consultarTodosAreaConcurso();
            String vagas;
            for (AreaConcurso area : areas) {
                modelo.addRow(new String[]{Long.toString(area.getId()),area.getNome(),area.getConcurso().getNomeConcurso(),vagas=Integer.toString(area.getVagas())});
            }
            TableListarAreaConcurso.setModel(modelo);
         } catch (Exception e) {
              JOptionPane.showMessageDialog(rootPane, e.getMessage());
         }
            
           
     }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        TableListarAreaConcurso = new javax.swing.JTable();
        buttonRemoverAreaConcurso = new javax.swing.JButton();
        buttonAlterarAreaConcurso = new javax.swing.JButton();
        ButtonCadastrarAreaConcurso = new javax.swing.JButton();
        ButtonListarAreaConcurso = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TableListarAreaConcurso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(TableListarAreaConcurso);

        buttonRemoverAreaConcurso.setText("Remover");
        buttonRemoverAreaConcurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoverAreaConcursoActionPerformed(evt);
            }
        });

        buttonAlterarAreaConcurso.setText("Alterar");
        buttonAlterarAreaConcurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAlterarAreaConcursoActionPerformed(evt);
            }
        });

        ButtonCadastrarAreaConcurso.setText("Cadastrar");
        ButtonCadastrarAreaConcurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonCadastrarAreaConcursoActionPerformed(evt);
            }
        });

        ButtonListarAreaConcurso.setText("Listar");
        ButtonListarAreaConcurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonListarAreaConcursoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(ButtonListarAreaConcurso, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(ButtonCadastrarAreaConcurso)
                        .addGap(24, 24, 24)
                        .addComponent(buttonRemoverAreaConcurso, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonAlterarAreaConcurso, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAlterarAreaConcurso)
                    .addComponent(buttonRemoverAreaConcurso)
                    .addComponent(ButtonCadastrarAreaConcurso)
                    .addComponent(ButtonListarAreaConcurso))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonRemoverAreaConcursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoverAreaConcursoActionPerformed
        // TODO add your handling code here:mo
        AreaConcurso areaConcurso= new AreaConcurso();
        try {
            int codigo = TableListarAreaConcurso.getSelectedRow();
            areaConcurso = fachada.consultarAreaConcursoPorId(Long.parseLong(TableListarAreaConcurso.getValueAt(codigo, 0).toString()));
            RemoverAreaConcurso rmvAC = new RemoverAreaConcurso(areaConcurso, this, null);  
            rmvAC.setVisible(true);
            this.carregarListaAreaConcurso();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }//GEN-LAST:event_buttonRemoverAreaConcursoActionPerformed

    private void buttonAlterarAreaConcursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAlterarAreaConcursoActionPerformed
        // TODO add your handling code here:
        AreaConcurso areaConcurso= new AreaConcurso();
        try {
            int codigo = TableListarAreaConcurso.getSelectedRow();
            areaConcurso = fachada.consultarAreaConcursoPorId(Long.parseLong(TableListarAreaConcurso.getValueAt(codigo, 0).toString()));
            AlterarAreaConcurso altAC = new AlterarAreaConcurso(areaConcurso, this);
                
           altAC.setVisible(true);
            this.carregarListaAreaConcurso();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());

    }
    
    }//GEN-LAST:event_buttonAlterarAreaConcursoActionPerformed

    private void ButtonCadastrarAreaConcursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonCadastrarAreaConcursoActionPerformed
        // TODO add your handling code here:
        CRUDAreaConcurso crdAC= new CRUDAreaConcurso(null);
      crdAC.setVisible(true);

    }//GEN-LAST:event_ButtonCadastrarAreaConcursoActionPerformed

    private void ButtonListarAreaConcursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonListarAreaConcursoActionPerformed
        // TODO add your handling code here:
        this.carregarListaAreaConcurso();
    }//GEN-LAST:event_ButtonListarAreaConcursoActionPerformed

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
            java.util.logging.Logger.getLogger(ListarAreaConcurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListarAreaConcurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListarAreaConcurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListarAreaConcurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListarAreaConcurso().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonCadastrarAreaConcurso;
    private javax.swing.JButton ButtonListarAreaConcurso;
    private javax.swing.JTable TableListarAreaConcurso;
    private javax.swing.JButton buttonAlterarAreaConcurso;
    private javax.swing.JButton buttonRemoverAreaConcurso;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
