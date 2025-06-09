class Command {
    execute() {}
    undo() {}
}

class StatusChangeCommand extends Command {
    constructor(task, newStatus) {
        super();
        this.task = task;
        this.prevStatus = task.getStatus();
        this.newStatus = newStatus;
    }

    execute() {
        this.task.setStatus(this.newStatus);
    }

    undo() {
        this.task.setStatus(this.prevStatus);
    }
}

class CommandManager {
    constructor() {
        this.history = [];
    }

    executeCommand(command) {
        command.execute();
        this.history.push(command);
    }

    undo() {
        if (this.history.length === 0) {
            alert("Nada para desfazer.");
            return;
        }

        const command = this.history.pop();
        command.undo();
    }
}
