package arts.command;

import arts.ArtsException;
import arts.task.Task;
import arts.task.TaskList;
import arts.util.Storage;
import arts.util.Ui;

/**
 * Represents a command to unmark a task, marking it as not done in the task list.
 */
public class UnmarkCommand implements Command {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;
    private final String taskIndex;

    /**
     * Constructs an UnmarkCommand with the specified task list, storage, UI, and task index.
     *
     * @param tasks The list of tasks.
     * @param storage The storage used to save tasks.
     * @param ui The user interface for displaying messages.
     * @param taskIndex The index of the task to be marked as not done.
     */
    public UnmarkCommand(TaskList tasks, Storage storage, Ui ui, String taskIndex) {
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command to unmark a task, marking it as not done. Parses the task index,
     * marks the task as not done, saves the updated task list to storage, and displays a
     * confirmation message. Throws an exception if the task index is invalid.
     *
     * @throws ArtsException If the task index is invalid or cannot be parsed.
     */
    @Override
    public String execute() throws ArtsException {
        try {
            int index = Integer.parseInt(taskIndex) - 1;
            Task task = tasks.getTask(index);
            task.markAsNotDone();
            storage.save(tasks.getTasks());
            return "OK, I've marked this task as not done yet:\n " + task;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new ArtsException("Invalid task index.");
        }
    }
}
