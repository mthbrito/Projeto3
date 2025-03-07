package locadora.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class JsonHandler {

    public static String getCaminhoArquivoJson(String nomeArquivo) {
        try {
            String arquivoJar = new File(System.getProperty("user.dir")).getAbsolutePath();
            return arquivoJar + nomeArquivo;
        } catch (RuntimeException e) {
            return null;
        }
    }

    protected <T> void atualizarArquivo(String arquivo, List<T> listaAtualizada) {
        String listaAtualizadaJson = new Gson().toJson(listaAtualizada);
        try (FileWriter writer = new FileWriter(arquivo)) {
            writer.write(listaAtualizadaJson);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao atualizar json: " + arquivo);
        }
    }

    protected <T> boolean isVazio(String arquivo, Class<T> classe) {
        try (FileReader reader = new FileReader(arquivo)) {
            Type type = TypeToken.getArray(classe).getType();
            T[] conteudo = new Gson().fromJson(reader, type);
            return conteudo.length == 0;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler json: " + arquivo);
        } catch (NullPointerException e) {
            return false;
        }
    }

    protected <T> List<T> getConteudo(String arquivo, Class<T> classe) {
        try (FileReader reader = new FileReader(arquivo)) {
            Type type = TypeToken.getArray(classe).getType();
            T[] conteudo = new Gson().fromJson(reader, type);
            return new ArrayList<>(Arrays.asList(conteudo));
        } catch (IOException | NullPointerException e) {
            return new ArrayList<>();
        }
    }
}
