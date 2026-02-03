package es.upm.grise.profundizacion.file;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class File {

    private FileType type;
    private List<Character> content;
    private FileUtils fileUtils;

    /*
     * Constructor
     */
    public File() {
        this.content = new ArrayList<>();
        this.fileUtils = new FileUtils();
    }

    /*
     * Constructor for testing / dependency injection
     */
    public File(FileUtils fileUtils) {
        this.content = new ArrayList<>();
        this.fileUtils = fileUtils;
    }

    /*
     * Method to code / test
     */
    public void addProperty(char[] content) {
        this.type = FileType.PROPERTY;

        if (content == null) {
            return;
        }

        for (char c : content) {
            this.content.add(c);
        }
    }

    /*
     * Method to code / test
     */
    public long getCRC32() {
        StringBuilder sb = new StringBuilder();
        for (Character c : this.content) {
            sb.append(c);
        }
        byte[] bytes = sb.toString().getBytes(StandardCharsets.UTF_8);

        return fileUtils.calculateCRC32(bytes);
    }

    /*
     * Setters/getters
     */
    public void setType(FileType type) {
        this.type = type;
    }

    public FileType getType() {
        return type;
    }

    public List<Character> getContent() {
        return content;
    }
	
}
