/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visual;

import controle.HoraData;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import modelo.GerenciadorArquivo;

/**
 *
 * @author christian
 */
public class JFICaixas extends javax.swing.JInternalFrame {
String dataHoje;
    /**
     * Creates new form JIFCaixas
     */
    public JFICaixas() {
        initComponents();
        HoraData hrdt = new HoraData();
        dataHoje = hrdt.mostraData();
        setLocation(1, 340);
        this.setSize(1140, 340);
        jbtnCxDia.setVerticalTextPosition(SwingConstants.BOTTOM);
        jbtnCxMes.setVerticalTextPosition(SwingConstants.BOTTOM);
        jbtnCxTotal.setVerticalTextPosition(SwingConstants.BOTTOM);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbtnCxMes = new javax.swing.JButton();
        jbtnCxTotal = new javax.swing.JButton();
        jbtnCxDia = new javax.swing.JButton();

        setClosable(true);
        setTitle("Caixa");
        setPreferredSize(new java.awt.Dimension(1110, 373));

        jbtnCxMes.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jbtnCxMes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visual/imagens/money_bag.png"))); // NOI18N
        jbtnCxMes.setText("Mês");
        jbtnCxMes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbtnCxMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCxMesActionPerformed(evt);
            }
        });

        jbtnCxTotal.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jbtnCxTotal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visual/imagens/money_bag.png"))); // NOI18N
        jbtnCxTotal.setText("Total");
        jbtnCxTotal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbtnCxTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCxTotalActionPerformed(evt);
            }
        });

        jbtnCxDia.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jbtnCxDia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visual/imagens/money_bag.png"))); // NOI18N
        jbtnCxDia.setText("Dia");
        jbtnCxDia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbtnCxDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCxDiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jbtnCxDia, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnCxMes, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtnCxTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jbtnCxDia, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
            .addComponent(jbtnCxMes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jbtnCxTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnCxDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCxDiaActionPerformed
        GerenciadorArquivo gerarq = new GerenciadorArquivo();
        String dataConsulta = dataHoje;
        try {
            gerarq.caixaDia(JOptionPane.showInputDialog("Data",dataConsulta));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JFICaixas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    }//GEN-LAST:event_jbtnCxDiaActionPerformed

    private void jbtnCxMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCxMesActionPerformed
         GerenciadorArquivo gerarq = new GerenciadorArquivo();
         String mesConsulta;
        try {
            String[] num = dataHoje.split("/");
            mesConsulta = num[1];
            gerarq.caixaMes(JOptionPane.showInputDialog("Mês nº",mesConsulta));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JFICaixas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnCxMesActionPerformed

    private void jbtnCxTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCxTotalActionPerformed
        GerenciadorArquivo gerarq = new GerenciadorArquivo();
        try {
            gerarq.caixaTotal();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JFICaixas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnCxTotalActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtnCxDia;
    private javax.swing.JButton jbtnCxMes;
    private javax.swing.JButton jbtnCxTotal;
    // End of variables declaration//GEN-END:variables
}
