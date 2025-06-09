/**
 * Proxy para proteger tarefas concluídas contra exclusão
 */
class TaskProxy {
    constructor(realTaskManager) {
        this.realTaskManager = realTaskManager;
    }

    deleteTask(task) {
        if (task.getStatus() === 'concluida') {
            alert(`A tarefa "${task.getTitle()}" está concluída e não pode ser excluída.`);
            return false;
        }
        return this.realTaskManager.deleteTask(task.getId());
    }
}
