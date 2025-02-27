package locadora.utils;

import java.time.LocalDate;

public class DataHandler {

    public String formatarData(String data) {
        String[] array = data.split("/");
        int dia = Integer.parseInt(array[0].trim());
        int mes = Integer.parseInt(array[1].trim());
        int ano = Integer.parseInt(array[2].trim());
        return ano + "-" + mes + "-" + dia;
    }

    public static LocalDate converterDataInserida(String data) {
        int dia = Integer.parseInt(data.split("/")[0]);
        int mes = Integer.parseInt(data.split("/")[1]);
        int ano = Integer.parseInt(data.split("/")[2]);
        return LocalDate.of(ano, mes, dia);
    }

    public static LocalDate converterDataArmazenada(String data) {
        int ano = Integer.parseInt(data.split("-")[0]);
        int mes = Integer.parseInt(data.split("-")[1]);
        int dia = Integer.parseInt(data.split("-")[2]);
        return LocalDate.of(ano, mes, dia);
    }
}
