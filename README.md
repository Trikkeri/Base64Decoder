# Base64Decoder
Aims to quickly decode base64 containing XML without extra button presses while copying decoded value to clipboard.
Accepts base64 input in 4 different forms:
- normal base64
- base64 with line breaks and encoded line break (&#13;) characters at the end of each line
- base64 in one long string where each line break is done with empty space instead of actual line break
- base64 where each new line starts with an empty space

decoded content is parsed to human readable form (indents, linebreaks) and copied to clipboard.

Does not currently work unless encoded content contains XML.