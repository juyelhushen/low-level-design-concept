package design_patterns.behavioural.command.solution;

import java.util.ArrayDeque;
import java.util.Deque;

public class Button {
    private  Command command;
    private final Deque<Command> undoStack = new ArrayDeque<>();
    private final Deque<Command> redoStack = new ArrayDeque<>();

    public Button(Command command) {
        this.command = command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void onClick() {
        command.execute();
        undoStack.push(command);
        redoStack.clear();
    }

    public void onUndoClick() {
        if (undoStack.isEmpty()) return;
        Command cmd = undoStack.pop();
        cmd.undo();
        redoStack.push(cmd);
    }

    public void redo() {
        if (redoStack.isEmpty()) return;
        Command cmd = redoStack.pop();
        cmd.execute(); // or cmd.redo() if you add that method
        undoStack.push(cmd);
    }
}
