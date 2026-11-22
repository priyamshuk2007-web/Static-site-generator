# Static Site Generator

A simple Java command-line static site generator that converts Markdown (`.md`) files into HTML pages using a shared HTML template.

## Features

- Recursively scans a source directory for Markdown files
- Converts Markdown files to HTML files
- Supports level 1 and level 2 headings
- Supports paragraphs and bold text with `**text**`
- Inserts generated content into `template.html`
- Writes every generated page to a `dist` directory

## Project Structure

```text
Static site generator/
├── Main.java
├── dir_read.java
├── MarkdownParser.java
├── Elementparse.java
├── heading1parser.java
├── heading2parser.java
├── paragraphparser.java
├── BoldTextParser.java
└── template.html
```

## How to Run

Requirements:

- Java Development Kit (JDK) 8 or newer

Compile all Java source files:

```bash
cd "Static site generator"
javac *.java
```

Run the generator:

```bash
java Main
```

Enter the path to a directory containing Markdown files when prompted.

For example:

```text
Enter path to Source Directory: /path/to/content
```

The generated HTML files will appear in:

```text
Static site generator/dist/
```

## Markdown Support

| Markdown | HTML |
|---|---|
| `# Heading` | `<h1>Heading</h1>` |
| `## Heading` | `<h2>Heading</h2>` |
| `Regular text` | `<p>Regular text</p>` |
| `**Bold text**` | `<strong>Bold text</strong>` |

## Template

Generated content is inserted into the `<body>` element of `template.html`. Update this file to change the generated pages' page structure or styling.

## Generated Output

Markdown filenames retain their names while changing from `.md` to `.html`. For example:

```text
example.md -> dist/example.html
```

## License

No license has been specified for this repository.
