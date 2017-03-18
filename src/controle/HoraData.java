package controle;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @author christian.souza@gmail.com
 */
public class HoraData {

    public String mostraData() {
        Date data = new Date();
        SimpleDateFormat dataF = new SimpleDateFormat("dd/MM/yyyy");
        String dataAtual = dataF.format(data);
        return dataAtual;
    }

    public String mostraHora() {
        Date data = new Date();
        SimpleDateFormat horaF = new SimpleDateFormat("hh:mm:ss");
        String horaAtual = horaF.format(data);
        return horaAtual;
    }

}
