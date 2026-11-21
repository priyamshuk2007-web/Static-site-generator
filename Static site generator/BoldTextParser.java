public class BoldTextParser extends Elementparse {

    public BoldTextParser(StringBuilder output) {
        super(output);
    }

    @Override
    public int parse(String input, int index) {
        // Skip the opening "**"
        index += 2;
        output.append("<strong>");

        while (index < input.length()) {
            char ch = input.charAt(index);

            // Found closing "**"
            if (ch == '*' && index + 1 < input.length() && input.charAt(index + 1) == '*') {
                output.append("</strong>");
                return index + 2; // Jump past closing "**" and return to caller
            }

            // Safety break if line ends before closing "**"
            if (ch == '\n') {
                output.append("</strong>");
                return index; // Let the parent block parser handle the '\n'
            }

            output.append(ch);
            index++;
        }

        output.append("</strong>");
        return index;
    }
}
