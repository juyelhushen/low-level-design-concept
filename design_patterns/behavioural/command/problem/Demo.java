package design_patterns.behavioural.command.problem;

public class Demo {

    public static void main(String[] args) {
        TextStyle style = new TextStyle();  //tightly coupled here
        BoldButton boldButton = new BoldButton(style);
        boldButton.onCLick();

        ItalicButton italicButton = new ItalicButton(style);
        italicButton.onClick();
    }
}
