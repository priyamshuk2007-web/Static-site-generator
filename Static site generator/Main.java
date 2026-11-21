import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter path to Source Directory: ");
        String sourcePath = sc.nextLine().trim();

        try {
            // 1. Collect all .md file paths using your dir_read class
            dir_read reader = new dir_read(sourcePath);
            ArrayList<String> mdFiles = reader.getdir();

            if (mdFiles.isEmpty()) {
                System.out.println("No .md files found in the directory.");
                return;
            }

            // 2. Ensure the dist/ output folder exists
            File distDir = new File("dist");
            if (!distDir.exists()) {
                distDir.mkdirs();
            }

            // 3. Loop through each .md file, parse, and export to dist/
            for (String filePath : mdFiles) {
                File mdFile = new File(filePath);
                StringBuilder rawMarkdown = new StringBuilder();

                // Read the .md file using BufferedReader
                try (BufferedReader br = new BufferedReader(new FileReader(mdFile))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        rawMarkdown.append(line).append("\n");
                    }
                }

                // Run your recursive index-based parser
                MarkdownParser parser = new MarkdownParser();
                parser.parse(rawMarkdown.toString(), 0);
                String finalHtml = parser.Html();

                // Convert filename from "example.md" to "example.html"
                String fileName = mdFile.getName();
                String htmlFileName = fileName.substring(0, fileName.lastIndexOf('.')) + ".html";
                File outFile = new File(distDir, htmlFileName);

                // Write compiled HTML to dist/ using BufferedWriter
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(outFile))) {
                    bw.write(finalHtml);
                }

                System.out.println("Compiled: " + outFile.getPath());
            }

            System.out.println("Done! All HTML files generated in dist/");

        } catch (IllegalArgumentException e) {
            System.err.println("Validation Error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("File I/O Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
