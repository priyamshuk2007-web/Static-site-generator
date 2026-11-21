public class MarkdownParser extends Elementparse {

    public MarkdownParser() {
        super();
    }

    @Override
    public int parse(String input, int index) {
        while (index < input.length()) {
            // Skip blank lines or carriage returns
            if (input.charAt(index) == '\n' || input.charAt(index) == '\r') {
                index++;
                continue;
            }

            Elementparse lineParser;
            if (input.startsWith("##", index)) {
                lineParser = new heading2parser(output);
            } else if (input.startsWith("#", index)) {
                lineParser = new heading1parser(output);
            } else {
                lineParser = new paragraphparser(output);
            }

            // Polymorphic call that advances index to the next line
            index = lineParser.parse(input, index);
        }
        return index;
    }
}