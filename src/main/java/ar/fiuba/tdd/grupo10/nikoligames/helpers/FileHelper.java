package ar.fiuba.tdd.grupo10.nikoligames.helpers;

import com.google.gson.Gson;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Helper to json's files.
 */
public final class FileHelper {

    public static Object getFileContent(String location, Class inputType) {

        Gson gson = new Gson();
        Path path = Paths.get(location);
        try {
            List<String> lines = Files.readAllLines(path);
            StringBuffer buffer = new StringBuffer();
            for (String line : lines) {
                buffer.append(line);
            }
            return gson.fromJson(buffer.toString(), inputType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
