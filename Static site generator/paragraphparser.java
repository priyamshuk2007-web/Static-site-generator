public class paragraphparser extends Elementparse {

    public paragraphparser(StringBuilder output) {
        super(output);
    }

    @Override
    public int parse(String input, int index) {
        output.append("<p>");

        while (index < input.length()) {
            char ch = input.charAt(index);

            if (ch == '\n') {
                output.append("</p>\n");
                return index + 1;
            }

            if (ch == '*' && index + 1 < input.length() && input.charAt(index + 1) == '*') {
                BoldTextParser boldParser = new BoldTextParser(output);
                index = boldParser.parse(input, index);
                continue;
            }

            output.append(ch);
            index++;
        }

        output.append("</p>\n");
        return index;
    }
}
