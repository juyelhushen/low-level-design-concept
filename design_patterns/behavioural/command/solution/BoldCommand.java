package design_patterns.behavioural.command.solution;

public class BoldCommand implements Command {

    private TextEditor editor;

    public BoldCommand(TextEditor editor) {
        this.editor = editor;
    }

    public void execute() {
        editor.boldText();
    }

    @Override
    public void undo() {
        editor.unboldText();
    }

}
