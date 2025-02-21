package locadora.Utils;

import java.time.LocalDate;
import java.util.Arrays;

public class Data {

    public String formataData(String data) {
        String[] array = data.split("/");
        System.out.println(Arrays.toString(array));
        int dia = Integer.parseInt(array[0].trim());
        int mes = Integer.parseInt(array[1].trim());
        int ano = Integer.parseInt(array[2].trim());
        return ano + "-" + mes + "-" + dia;
    }
}
