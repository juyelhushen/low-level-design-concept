package design_patterns.behavioural.command.solution;

public class ItalicCommand implements Command{
    private final TextEditor editor;

    public ItalicCommand(TextEditor editor) {
        this.editor = editor;
    }

    public void execute() {
        editor.italicText();
    }

    @Override
    public void undo() {
        editor.unItalicText();
    }
}
