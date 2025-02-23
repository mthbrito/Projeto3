package locadora.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class JsonHandler {

    protected <T> boolean isVazio(String arquivo, Class<T> classe) {
        try (FileReader reader = new FileReader(arquivo)) {
            Type type = TypeToken.getArray(classe).getType();
            T[] conteudo = new Gson().fromJson(reader, type);
            return conteudo.length == 0;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
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
