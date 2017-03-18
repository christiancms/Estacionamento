/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import javax.swing.JOptionPane;

/**
 *
 * @author christian
 */
public class Erros {

    static void MostraErro(int erro) {
        final String SUCESSO = "Okay, operação realizada com sucesso!";// 0
        final String CHEIA = "ERRO: Lista cheia!";// -1
        final String VAZIA = "ERRO: Lista vazia!";// -2
        final String EXCEDEU = "Erro: Excedeu o limite máximo!";// -3
        final String NLOCALIZADO = "Erro: Regristro não encontrado"; //-4
        switch (erro) {
            case 0:
                JOptionPane.showMessageDialog(null, SUCESSO, null, JOptionPane.INFORMATION_MESSAGE);
                break;
            case -1:
                JOptionPane.showMessageDialog(null, CHEIA, null, JOptionPane.INFORMATION_MESSAGE);
                break;
            case -2:
                JOptionPane.showMessageDialog(null, VAZIA, null, JOptionPane.INFORMATION_MESSAGE);
                break;
            case -3:
                JOptionPane.showMessageDialog(null, EXCEDEU, null, JOptionPane.INFORMATION_MESSAGE);
                break;
            case -4:
                JOptionPane.showMessageDialog(null, NLOCALIZADO, null, JOptionPane.INFORMATION_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Atenção: Erro Desconhecido!", null, JOptionPane.ERROR_MESSAGE);
                break;
        }

    }
}
