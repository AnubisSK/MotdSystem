package sk.anubissvk.motdsystem.configuration;

import com.google.common.base.Charsets;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class Vlastnakonfiguracia extends YamlConfiguration {
    private Map<String, List<String>> comments = null;

    private boolean newLineAfterHeader = false;

    private boolean newLinePerKey = false;

    private Vlastnakonfiguracia() {
        this.comments = new LinkedHashMap<>();
    }

    public void addDefault(String path, Object defaultValue, String... comments) {
        if (!contains(path))
            set(path, defaultValue, comments);
    }

    public ConfigurationSection createSection(String path, String... comments) {
        if (path != null && comments != null && comments.length > 0) {
            List<String> commentsList = new ArrayList<>();
            byte b;
            int i;
            String[] arrayOfString;
            for (i = (arrayOfString = comments).length, b = 0; b < i; ) {
                String comment = arrayOfString[b];
                if (comment != null) {
                    commentsList.add(comment);
                } else {
                    commentsList.add("");
                }
                b++;
            }
            this.comments.put(path, commentsList);
        }
        return createSection(path);
    }

    public void addDefault(String path, Object value) {
        if (!contains(path))
            set(path, value);
    }

    public void load(File file) throws IOException, InvalidConfigurationException {
        super.load(file);
        BufferedReader configReader = null;
        List<String> configLines = new ArrayList<>();
        try {
            configReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charsets.UTF_8));
            String configReadLine;
            while ((configReadLine = configReader.readLine()) != null)
                configLines.add(configReadLine);
        } finally {
            if (configReader != null)
                configReader.close();
        }
        boolean hasHeader = !(configLines.size() >= 2 && trim(configLines.get(1)).isEmpty());
        String firstComment = null;
        boolean secondComment = false;
        boolean hasFirstComment = false;
        Map<String, List<String>> configComments = new LinkedHashMap<>();
        for (int lineIndex = 0; lineIndex < configLines.size(); lineIndex++) {
            String configLine = configLines.get(lineIndex);
            String trimmedLine = trimPrefixSpaces(configLine);
            if (trimmedLine.startsWith("#") && lineIndex >= 0 && lineIndex <= 10 && !secondComment) {
                if (lineIndex == 0)
                    hasFirstComment = true;
                if (!hasFirstComment)
                    continue;
                String configKey = getPathToComment(configLines, lineIndex, configLine);
                if (firstComment != null) {
                    if (firstComment.equals(configKey))
                        continue;
                    secondComment = true;
                } else {
                    firstComment = configKey;
                    continue;
                }
            }
            if (trimmedLine.startsWith("#") && (lineIndex > 0 || !hasHeader)) {
                String configKey = getPathToComment(configLines, lineIndex, configLine);
                if (configKey != null) {
                    List<String> keyComments = configComments.get(configKey);
                    if (keyComments == null)
                        keyComments = new ArrayList<>();
                    keyComments.add(trimmedLine.substring(trimmedLine.startsWith("# ") ? 2 : 1));
                    configComments.put(configKey, keyComments);
                }
            }
            continue;
        }
        this.comments = configComments;
    }

    public void load(File file, boolean loadComments) throws IOException, InvalidConfigurationException {
        if (loadComments) {
            load(file);
        } else {
            super.load(file);
        }
    }

    public void save(File file) throws IOException {
        super.save(file);
        List<String> configContent = new ArrayList<>();
        BufferedReader configReader = null;
        try {
            configReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charsets.UTF_8));
            String configReadLine;
            while ((configReadLine = configReader.readLine()) != null)
                configContent.add(configReadLine);
        } finally {
            if (configReader != null)
                configReader.close();
        }
        BufferedWriter bufferedWriter = null;
        boolean isHeaderCommentInserted = false;
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), Charsets.UTF_8));
            bufferedWriter.write("");
            for (int lineIndex = 0; lineIndex < configContent.size(); lineIndex++) {
                String configLine = configContent.get(lineIndex);
                String trimmedLine = trimPrefixSpaces(configLine);
                String configKey = null;
                if (!trimmedLine.startsWith("#") && configLine.contains(":")) {
                    configKey = getPathToKey(configContent, lineIndex, configLine);
                    if (!isHeaderCommentInserted)
                        isHeaderCommentInserted = true;
                }
                if (configKey != null && this.comments.containsKey(configKey)) {
                    int numOfSpaces = getPrefixSpaceCount(configLine);
                    String spacePrefix = "";
                    for (int i = 0; i < numOfSpaces; i++)
                        spacePrefix = String.valueOf(spacePrefix) + " ";
                    List<String> configComments = this.comments.get(configKey);
                    if (configComments != null)
                        for (String comment : configComments) {
                            bufferedWriter.append(spacePrefix).append("# ").append(comment);
                            bufferedWriter.newLine();
                        }
                } else if (!isHeaderCommentInserted && configKey == null && configLine.startsWith("#")) {
                    bufferedWriter.append(configLine);
                    bufferedWriter.newLine();
                }
                if (!trimmedLine.startsWith("#")) {
                    bufferedWriter.append(configLine);
                    bufferedWriter.newLine();
                }
                boolean isComment = trimmedLine.startsWith("#");
                if (this.newLineAfterHeader && lineIndex == 0 && isComment) {
                    bufferedWriter.newLine();
                } else if (this.newLinePerKey && lineIndex < configContent.size() - 1 && !isComment) {
                    String nextConfigLine = configContent.get(lineIndex + 1);
                    if (nextConfigLine != null && !nextConfigLine.startsWith(" ") &&
                            !nextConfigLine.startsWith("'") && !nextConfigLine.startsWith("-"))
                        bufferedWriter.newLine();
                }
            }
        } finally {
            if (bufferedWriter != null)
                bufferedWriter.close();
        }
    }

    public void set(String key, Object value, String... comments) {
        if (value != null) {
            if (comments != null)
                if (comments.length > 0) {
                    List<String> commentsList = new ArrayList<>();
                    byte b;
                    int i;
                    String[] arrayOfString;
                    for (i = (arrayOfString = comments).length, b = 0; b < i; ) {
                        String comment = arrayOfString[b];
                        if (comment != null) {
                            commentsList.add(comment);
                        } else {
                            commentsList.add("");
                        }
                        b++;
                    }
                    this.comments.put(key, commentsList);
                } else {
                    this.comments.remove(key);
                }
        } else {
            this.comments.remove(key);
        }
        set(key, value);
    }

    public static Vlastnakonfiguracia loadConfiguration(File file) {
        Vlastnakonfiguracia config = new Vlastnakonfiguracia();
        try {
            config.load(file);
        } catch (FileNotFoundException fileNotFoundException) {

        } catch (IOException|InvalidConfigurationException ex) {
            Bukkit.getLogger().log(Level.SEVERE, "Nedá sa načítať " + file, ex);
        }
        return config;
    }

    private static String getPathToComment(List<String> configContents, int lineIndex, String configLine) {
        if (configContents != null && lineIndex >= 0 && lineIndex < configContents.size() - 1 && configLine != null) {
            String trimmedConfigLine = trimPrefixSpaces(configLine);
            if (trimmedConfigLine.startsWith("#")) {
                int currentIndex = lineIndex;
                while (currentIndex < configContents.size() - 1) {
                    currentIndex++;
                    String currentLine = configContents.get(currentIndex);
                    String trimmedCurrentLine = trimPrefixSpaces(currentLine);
                    if (!trimmedCurrentLine.startsWith("#")) {
                        if (trimmedCurrentLine.contains(":"))
                            return getPathToKey(configContents, currentIndex, currentLine);
                        break;
                    }
                }
            }
        }
        return null;
    }

    private static String getPathToKey(List<String> configContents, int lineIndex, String configLine) {
        if (configContents != null && lineIndex >= 0 && lineIndex < configContents.size() && configLine != null &&
                !configLine.startsWith("#") && configLine.contains(":")) {
            int spacesBeforeKey = getPrefixSpaceCount(configLine);
            String key = trimPrefixSpaces(configLine.substring(0, configLine.indexOf(':')));
            if (spacesBeforeKey > 0) {
                int currentIndex = lineIndex;
                int previousSpacesBeforeCurrentLine = -1;
                boolean atZeroSpaces = false;
                while (currentIndex > 0) {
                    currentIndex--;
                    String currentLine = configContents.get(currentIndex);
                    int spacesBeforeCurrentLine = getPrefixSpaceCount(currentLine);
                    if (trim(currentLine).isEmpty())
                        break;
                    if (!trim(currentLine).startsWith("#") &&
                            spacesBeforeCurrentLine < spacesBeforeKey &&
                            currentLine.contains(":")) {
                        if (spacesBeforeCurrentLine > 0 || !atZeroSpaces) {
                            if (spacesBeforeCurrentLine == 0)
                                atZeroSpaces = true;
                            if (previousSpacesBeforeCurrentLine == -1 ||
                                    spacesBeforeCurrentLine < previousSpacesBeforeCurrentLine) {
                                previousSpacesBeforeCurrentLine = spacesBeforeCurrentLine;
                                key = String.valueOf(trimPrefixSpaces(currentLine.substring(0, currentLine.indexOf(":")))) +
                                        "." + key;
                            }
                            continue;
                        }
                        break;
                    }
                }
            }
            return key;
        }
        return null;
    }

    private static int getPrefixSpaceCount(String aString) {
        int spaceCount = 0;
        if (aString != null && aString.contains(" ")) {
            byte b;
            int i;
            char[] arrayOfChar;
            for (i = (arrayOfChar = aString.toCharArray()).length, b = 0; b < i; ) {
                char aCharacter = arrayOfChar[b];
                if (aCharacter == ' ') {
                    spaceCount++;
                    b++;
                }
                break;
            }
        }
        return spaceCount;
    }

    private static String trim(String aString) {
        return (aString != null) ? aString.trim().replace(System.lineSeparator(), "") : "";
    }

    private static String trimPrefixSpaces(String aString) {
        if (aString != null)
            while (aString.startsWith(" "))
                aString = aString.substring(1);
        return aString;
    }
}
