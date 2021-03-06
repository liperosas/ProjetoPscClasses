/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import classes.CartaoResposta;
import classes.Concursando;
import classes.Fase;
import classes.Questao;
import classes.QuestaoMultiplaEscolha;
import fachada.Fachada;
import fachada.IFachada;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Antônio
 */
public class ListaClassificacao extends javax.swing.JFrame {

    /**
     * Creates new form ListaClassificação
     */
    List<Concursando> concursandos;
    double[] medias;
    IFachada fachada = Fachada.obterInstancia();
    Fase fase;

    public ListaClassificacao(Fase fase) {
        initComponents();
         try {
            fase = fachada.consultarFasePorId(fase.getId());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
        this.fase = fase;
        concursandos = new ArrayList<Concursando>();
        try {
            concursandos = fachada.calcularNotaMultiplaConcursandos(fase);
            medias = new double[concursandos.size()];
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        if (!fase.isClassificacaoRealizada()) {
            fase.setClassificacaoRealizada(true);
        }
        this.classificarConcursandos();
        this.carregarListaConcursandos();
    }

    private ListaClassificacao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void carregarListaConcursandos() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new String[]{"Colocação", "Nome", "Media"});
        int i = 0;
        for (Concursando concursando : concursandos) {
            DecimalFormat df = new DecimalFormat("#.##");           
            modelo.addRow(new Object[]{(i + 1) + "º", concursando.getNome(),(String.format("%.02f",medias[i]))});
            i++;
        }
        TableConcursandosClassificacao.setModel(modelo);
    }

    public void classificarConcursandos() {
        int i = 0;
        for (Concursando concursando : concursandos) {
            double notaMultipla = 0.00;
            double notaDiscursiva = 0.00;
            double qtdProvas = 0.0;
            double notas = 0;
            for (CartaoResposta cartaoResposta : concursando.getCartoesResposta()) {
                if (cartaoResposta.getProva().getDiaFase().getFase().getId() == fase.getId()) {
                    qtdProvas++;
                    double qtdMult = 0.0;
                    double qtdDisc = 0.0;
                    for (Questao questao : cartaoResposta.getProva().getQuestoes()) {
                        if (questao instanceof QuestaoMultiplaEscolha) {
                            qtdMult++;
                        } else {
                            qtdDisc++;
                        }
                    }
                    notaMultipla = (cartaoResposta.getCorretaMultiplaEscolha() * cartaoResposta.getProva().getPesoMultipla()) / qtdMult;
                    notaDiscursiva = (cartaoResposta.getCorretaDiscursiva() * cartaoResposta.getProva().getPesoDiscursiva()) / qtdDisc;
                    notas += notaDiscursiva + notaMultipla;
                }
            }
            medias[i] = notas / qtdProvas;
            i++;


        }
        for (int j = 0; j < medias.length; j++) {
            for (int x = 0; x < medias.length; x++) {
                if (medias[j] > medias[x]) {
                    double media2 = medias[j];
                    medias[j] = medias[x];
                    medias[x] = media2;
                    Concursando c = new Concursando();
                    c = concursandos.get(j);
                    concursandos.set(j, concursandos.get(x));
                    concursandos.set(x, c);
                }
            }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        TableConcursandosClassificacao = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        TableConcursandosClassificacao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(TableConcursandosClassificacao);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ListaClassificacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListaClassificacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListaClassificacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListaClassificacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListaClassificacao().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableConcursandosClassificacao;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
