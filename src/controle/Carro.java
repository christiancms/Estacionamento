package controle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Carro {

    private int id, tipo;
    private String placa, modelo, data, hentrada, hsaida, tempo, valor;
    private File arquivo;

    public Carro(int id, String placa, int tipo, String modelo, String data, String hentrada) {
        setId(id);
        //this.placa = placa;
        setPlaca(placa);
        //this.tipo = tipo;
        setTipo(tipo);
        //this.modelo = modelo;
        setModelo(modelo);
        setData(data);
        setHentrada(hentrada);
        //arquivo = new File("/home/christian/banco.csv");
        arquivo = new File("banco.csv");
        try {
            arquivo.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Carro.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     public Carro(String hsaida, String tempo, String valor){
     setHsaida(hsaida);
     setTempo(tempo);
     setValor(valor);
     } 

     public Carro(){}// contrutor vazio para
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public int getTipo() {
        return tipo;
    }
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getHentrada() {
        return hentrada;
    }
    public void setHentrada(String hentrada) {
        this.hentrada = hentrada;
    }
    public String getHsaida() {
        return hsaida;
    }
    public void setHsaida(String hsaida) {
        this.hsaida = hsaida;
    }
    public String getTempo() {
        return tempo;
    }
    public void setTempo(String tempo) {
        this.tempo = tempo;
    }
    public String getValor() {
        return valor;
    }
    public void setValor(String valor) {
        this.valor = valor;
    }
    
    
    public void salvarCarro() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, true));
        bw.write(String.format("%d,%s;%d;%s;%s;%s", id, placa, tipo, modelo, data, hentrada));
        bw.newLine();
        bw.close();
    }

    private static int countLines;

    public static int getSize() {
        return countLines;
    }

    public static String[][] listarCarros() {

        BufferedReader br = null;
        String[][] carros = null;

        //File arquivo = new File("/home/christian/banco.csv");
        File arquivo = new File("banco.csv");
        try {
            arquivo.createNewFile();
            countLines = 0;
            try {
                br = new BufferedReader(new FileReader(arquivo));

                carros = new String[50][9];

                int i = 0;
                while (br.ready()) {
                    countLines++;
                    String[] linha = br.readLine().split(";");
                    carros[i][0] = (linha[0]);
                    carros[i][1] = (linha[1]);
                    carros[i][2] = (linha[2]);
                    carros[i][3] = (linha[3]);
                    carros[i][4] = (linha[4]);
                    carros[i][5] = (linha[5]);
                    carros[i][6] = (linha[6]);
                    carros[i][7] = (linha[7]);
                    carros[i][8] = (linha[8]);

                    i++;

                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Carro.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Carro.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(Carro.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(Carro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return carros;
    }

    @Override
    public String toString() {
        return "Veículo{" + "id=" + id + "placa=" + placa + ", tipo=" + tipo + ", modelo=" + modelo
                + ", data=" + data + ", hentrada=" + hentrada
                + ", hsaida=" + hsaida + ", tempo=" + tempo + ", valor=" + valor + '}';
    }

    

}
