import java.io.*;

public abstract class Elementparse {
    protected StringBuilder output;

    // Used by the root parser
    public Elementparse() {
        this.output = new StringBuilder();
    }

    // Used by child parsers so they append to the SAME StringBuilder
    public Elementparse(StringBuilder output) {
        this.output = output;
    }

    public abstract int parse(String input, int index);

    public final String Html() {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("template.html"))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not read template file", e);
        }

        String template = content.toString();
        int position = template.indexOf("<body>");
        if (position == -1)
            throw new IllegalArgumentException("Body tag not found");
        position += "<body>".length();

        return template.substring(0, position)
                + "\n" + output.toString()
                + template.substring(position);
    }
}