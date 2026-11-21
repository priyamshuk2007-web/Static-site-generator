public class heading2parser extends Elementparse {

    public heading2parser(StringBuilder output) {
        super(output); // Now actually shares the StringBuilder!
    }

    @Override
    public int parse(String input, int index) {
        // Skip "# " (or "#" if there's no space)
        index += (index + 2 < input.length() && input.charAt(index + 2) == ' ') ? 3 : 1;

        output.append("<h2>");

        while (index < input.length()) {
            char ch = input.charAt(index);

            if (ch == '\n') {
                output.append("</h2>\n");
                return index + 1;
            }

            // Recursive call to BoldTextParser when "**" is hit
            if (ch == '*' && index + 1 < input.length() && input.charAt(index + 1) == '*') {
                BoldTextParser boldParser = new BoldTextParser(output);
                index = boldParser.parse(input, index);
                continue;
            }

            output.append(ch);
            index++;
        }

        output.append("</h2>\n");
        return index;
    }
}
