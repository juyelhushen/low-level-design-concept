package design_patterns.behavioural.command.problem;

public class BoldButton {
    private final TextStyle textStyle;

    public BoldButton(TextStyle textStyle) {
        this.textStyle = textStyle;
    }

    public void onCLick() {
        textStyle.boldText();
    }
}
