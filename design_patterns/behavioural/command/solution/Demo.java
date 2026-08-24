package design_patterns.behavioural.command.solution;

public class Demo {

    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();

        // Create command objects
        Command boldCommand = new BoldCommand(textEditor);
        Command italicCommand = new ItalicCommand(textEditor);

        // Execute commands
        Button button = new Button(boldCommand);
        button.onClick();
        button.onUndoClick();

        button.setCommand(italicCommand);
        button.onClick();
        button.onUndoClick();
    }
}
