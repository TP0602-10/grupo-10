package ar.fiuba.tdd.grupo10.nikoligames.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper to json files.
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

    public static void writeToFile(Object output, String location) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();;
        Path path = Paths.get(location);
        String json = gson.toJson(output);
        List<String> lines = new ArrayList<>();
        lines.add(json);
        try {
            Files.write(path, lines, Charset.forName("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
