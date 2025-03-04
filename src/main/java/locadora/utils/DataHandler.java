package locadora.utils;

import java.time.LocalDate;

public class DataHandler {

    public static LocalDate converterDataInserida(String dataInserida) throws RuntimeException {
        String[] data = dataInserida.split("/");
        int dia = Integer.parseInt(data[0]);
        int mes = Integer.parseInt(data[1]);
        int ano = Integer.parseInt(data[2]);
        return LocalDate.of(ano, mes, dia);
    }

    public static LocalDate converterDataArmazenada(String data) {
        int ano = Integer.parseInt(data.split("-")[0]);
        int mes = Integer.parseInt(data.split("-")[1]);
        int dia = Integer.parseInt(data.split("-")[2]);
        return LocalDate.of(ano, mes, dia);
    }

    public String formatarData(String data) {
        String[] array = data.split("/");
        int dia = Integer.parseInt(array[0].trim());
        int mes = Integer.parseInt(array[1].trim());
        int ano = Integer.parseInt(array[2].trim());
        return ano + "-" + mes + "-" + dia;
    }
}
