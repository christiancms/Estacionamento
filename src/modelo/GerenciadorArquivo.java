package modelo;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author christian
 */
public class GerenciadorArquivo extends JFrame {

    static final int diaS = 84600;
    static final int horaS = 3600;
    static final int minutoS = 60;
    //private String[] vetorLinha;
    BufferedWriter bw = null;
    BufferedReader br = null;

    public String codStr = null;
    public int cod;
    public String ultimo;
    public String linhaPesq = null;
    String tmpHEntrada;
    int tempoEstacionado, tempoHoras;

    //File meuBanco = new File(System.getProperty("user.home") + "/banco.csv");
    File meuBanco = new File("banco.csv");
    private ArrayList<String> vetorEdicao = new ArrayList<>();
    private ArrayList<String> vetorPesquisa = new ArrayList<>();
    //private String[] vetorEdicao;

    public int leitura() {
        codStr = null;
        String linha = null;
        int numLinhas = 0;
        try {
            LineNumberReader lnr = new LineNumberReader(new FileReader(meuBanco));
            lnr.skip(Long.MAX_VALUE);
            numLinhas = lnr.getLineNumber();
        } catch (Exception e) {
        }
        try {
            br = new BufferedReader(new FileReader(meuBanco));

            while ((linha = br.readLine()) != null) {
                String[] valores = linha.split(";");
                codStr = valores[0];
                ultimo = valores[valores.length - 1];
            }
            if ((linha = br.readLine()) == null) {
                codStr = "0";
            }
        } catch (Exception erro) {
            return -4;
        }
        cod = numLinhas;
        //cod = Integer.parseInt(codStr);
        return 0;
    }

    public int escreve(String placa, String tipo, String modelo, String data, String hentrada) {
        String hsaida = "0", tempo = "0", valor = "0";
        int i = 1;
        int numLinhas = 0;
        try {
            if (!meuBanco.exists()) {
                return -4;
            } else {
                leitura();

                try {
                    hentrada = hentrada.replace(hentrada.substring(hentrada.length() - 3), ""); //hora sem os segundos
                    placa = placa.toUpperCase();   // converte para letras maiúsculas
                    modelo = modelo.toUpperCase(); // converte o modelo para letras maiúsculas
                    bw = new BufferedWriter(new FileWriter(meuBanco, true));

                    bw.write((cod + i) + ";" + placa + ";" + tipo + ";" + modelo + ";"
                            + data + ";" + hentrada + ";" + hsaida + ";" + tempo + ";" + valor);
                    bw.newLine();
                    bw.close();
                } catch (Exception erro) {
                    return -4;
                }
                try {
                    LineNumberReader lnr = new LineNumberReader(new FileReader(meuBanco));
                    lnr.skip(Long.MAX_VALUE);
                    numLinhas = lnr.getLineNumber();
                } catch (Exception e) {
                }
                return 0;
            }
        } catch (Exception erro) {
            return -4;
        }

    }

    public static String[][] listar() {
        String linha = null;
        String[] valores = null;
        String[][] lista = null;

        BufferedReader br = null;
        File arquivo = new File("banco.csv");

        try {
            arquivo.createNewFile();

            try {
                br = new BufferedReader(new FileReader(arquivo));
                lista = new String[50][9];
                int i = 0;
                while (br.ready()) {
                    valores = br.readLine().split(";");

                    if (valores[6].equals("0")) {
                        lista[i][0] = (valores[0]);
                        lista[i][1] = (valores[1]);
                        lista[i][2] = (valores[2]);
                        lista[i][3] = (valores[3]);
                        lista[i][4] = (valores[4]);
                        lista[i][5] = (valores[5]);
                        lista[i][6] = (valores[6]);
                        lista[i][7] = (valores[7]);
                        lista[i][8] = (valores[8]);
                    }

                }

            } catch (Exception e) {
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro Leitura: " + erro.getMessage());
        }
        return lista;
    }

    public void alterar() {
        System.out.println("Inicio do método Alterar");
        String linha = null;
        try {
            br = new BufferedReader(new FileReader(meuBanco));
            while ((linha = br.readLine()) != null) {
                vetorEdicao.add(linha);
            }
        } catch (Exception e) {
        }
        //String valorLinha = "1;IPF4335;1;CORSA SEDAN;12/10/2015;15:17;15:47;00:30;0";
        String valorLinha = "21;KMS7214;1;CORSA;31/10/2015;09:39;0;0;0";
        String novoValorLinha = "21;KMS7214;1;CORSA;31/10/2015;09:39;09:59;0:20;0";
        if (vetorEdicao.contains(valorLinha)) {
            int numIndice = vetorEdicao.indexOf(valorLinha);
            System.out.println("Encontrado!!!\nLinha num:" + numIndice);
            vetorEdicao.set(numIndice, novoValorLinha);
        }

        try {
            Writer w = new FileWriter(meuBanco);
            w.write("");
            w.flush();
            w.close();
            bw = new BufferedWriter(new FileWriter(meuBanco, true));
            for (int i = 0; i < vetorEdicao.size(); i++) {
                bw.write(vetorEdicao.get(i));
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
        }

    }

    public void saida(String hentrada, String hsaida) {
        String linha = null;
        try {
            br = new BufferedReader(new FileReader(meuBanco));
            while ((linha = br.readLine()) != null) {
                vetorEdicao.add(linha);
            }
        } catch (Exception e) {
        }
        hsaida = hsaida.replace(hsaida.substring(hsaida.length() - 3), ""); //hora sem os segundos
        hentrada = tmpHEntrada;
        calculaTempo(hentrada, hsaida);
        int tmpHH = tempoEstacionado / 60;
        int tmpMM = tempoEstacionado % 60;
        String tempo;
        if (tmpMM < 10) {
            tempo = "" + tmpHH + ":0" + tmpMM;
        } else {
            tempo = "" + tmpHH + ":" + tmpMM;
        }
        double valor = tempoHoras * 5;
        String valorLinha = linhaPesq;
        String[] linhaPesqPartes;
        linhaPesqPartes = linhaPesq.split(";");
        String novoValorLinha = linhaPesqPartes[0] + ";" + linhaPesqPartes[1] + ";" + linhaPesqPartes[2] + ";"
                + linhaPesqPartes[3] + ";" + linhaPesqPartes[4] + ";" + linhaPesqPartes[5] + ";" + hsaida + ";" + tempo + ";" + valor;
        if (vetorEdicao.contains(valorLinha)) {
            int numIndice = vetorEdicao.indexOf(valorLinha);
            //System.out.println("Encontrado!!!\nLinha num:" + numIndice);
            vetorEdicao.set(numIndice, novoValorLinha);
        }

        try {
            Writer w = new FileWriter(meuBanco);
            w.write("");
            w.flush();
            w.close();
            bw = new BufferedWriter(new FileWriter(meuBanco, true));
            for (int i = 0; i < vetorEdicao.size(); i++) {
                bw.write(vetorEdicao.get(i));
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
        }

    }

    public void pesquisar(String texto, String hsaida) {

        String placaPesq = null;
        String[] valores = null;
        br = null;
        int i = 0;
        try {
            br = new BufferedReader(new FileReader(meuBanco));
            while ((linhaPesq = br.readLine()) != null) {
                valores = linhaPesq.split(";");
                if (valores[1].equalsIgnoreCase(texto) && valores[6].equals("0")) {
                    i++;
                    placaPesq = texto;
                    vetorPesquisa.add(linhaPesq);
                    tmpHEntrada = valores[5];
                    saida(tmpHEntrada, hsaida);
                }
            }
        } catch (IOException | HeadlessException erro) {
            JOptionPane.showMessageDialog(null, "Erro Leitura: " + erro.getMessage());
        }
        if (!(vetorPesquisa.isEmpty())) {
            JOptionPane.showMessageDialog(null, vetorPesquisa, "Resultado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "[" + texto + "] não encontrado", "Resultado", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calculaTempo(String hentrada, String hsaida) {
        String[] hentMin = hentrada.split(":");
        String[] hsaiMin = hsaida.split(":");
        int he = (Integer.parseInt(hentMin[0]) * minutoS) + Integer.parseInt(hentMin[1]);
        int hs = (Integer.parseInt(hsaiMin[0]) * minutoS) + Integer.parseInt(hsaiMin[1]);
        if (he > hs) {
            tempoHoras = 10;
        } else {
            tempoEstacionado = hs - he;
            tempoHoras = tempoEstacionado / 60;

        }
        //Integer.parseInt(codStr);
    }

    public void caixaDia(String hoje) throws FileNotFoundException {
        String[] valores = null;
        double totalDia = 0.0;
        br = new BufferedReader(new FileReader(meuBanco));
        try {
            while ((linhaPesq = br.readLine()) != null) {
                valores = linhaPesq.split(";");
                if (valores[4].equalsIgnoreCase(hoje)) {
                    String tmpValor = valores[8];
                    totalDia += Double.parseDouble(tmpValor);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(GerenciadorArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Total R$ " + totalDia, "Caixa do dia!", JOptionPane.INFORMATION_MESSAGE);
    }

    public void caixaMes(String mes) throws FileNotFoundException {
        if (mes.length() != 2) {
            mes = "0"+mes;
        }
        String[] valores = null;
        double totalMes = 0.0;
        br = new BufferedReader(new FileReader(meuBanco));
        try {
            while ((linhaPesq = br.readLine()) != null) {
                valores = linhaPesq.split(";");
                String[] numMes = valores[4].split("/");
                if (numMes[1].equalsIgnoreCase(mes)) {
                    String tmpValor = valores[8];
                    totalMes += Double.parseDouble(tmpValor);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(GerenciadorArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Total R$ " + totalMes, "Caixa do mês!", JOptionPane.INFORMATION_MESSAGE);
    }

    public void caixaTotal() throws FileNotFoundException {
        String[] valores;
        double total = 0.0;
        br = new BufferedReader(new FileReader(meuBanco));
        try {
            while ((linhaPesq = br.readLine()) != null) {
                valores = linhaPesq.split(";");
                String tmpValor = valores[8];
                total += Double.parseDouble(tmpValor);
            }

        } catch (IOException ex) {
            Logger.getLogger(GerenciadorArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Total R$ " + total, "Caixa Total!", JOptionPane.INFORMATION_MESSAGE);
    }
}
