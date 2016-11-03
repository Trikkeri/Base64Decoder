# Base64Decoder
Decodes Base64 content without extra button presses. Accepts base64 input in 3 different forms:
- base64 with linebreaks and encoded linebreak characters at the end each line
- base64 in one long string where each supposed linebreak is done with empty space
- base64 with linebreaks that have as empty space at the beginning of each line

If the decoded content is xml, it is parsed to human readable form (indents, linebreaks) and copied to clipboard.
