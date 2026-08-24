package design_patterns.behavioural.command.problem;

public class ItalicButton {

    private final TextStyle textStyle;

    public ItalicButton(TextStyle textStyle) {
        this.textStyle = textStyle;
    }

    public void onClick() {
        textStyle.italicText();
    }
}
