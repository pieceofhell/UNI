class UIController {
    constructor() {
        this.tasks = [];
        this.taskFactory = new TaskFactory();
        this.taskSubject = new TaskSubject();
        this.notifications = [];
        this.settings = new AppSettings(); // Singleton
        this.commandManager = new CommandManager(); // Command pattern

        this.initObservers();
        this.setupEventListeners();

        // Garante que os observadores estejam em sincronia com o HTML
        this.updateObservers();
    }

    initObservers() {
        if (document.getElementById('screen-observer').checked) {
            this.taskSubject.addObserver(new ScreenObserver(this.addNotification.bind(this)));
        }

        if (document.getElementById('email-observer').checked) {
            this.taskSubject.addObserver(new EmailObserver(this.addNotification.bind(this)));
        }

        if (document.getElementById('log-observer').checked) {
            this.taskSubject.addObserver(new LogObserver(this.addNotification.bind(this)));
        }

        if (document.getElementById('storage-observer').checked) {
            this.taskSubject.addObserver(new StorageObserver(this.addNotification.bind(this)));
        }
    }

    setupEventListeners() {
        // Submissão do formulário: criar nova tarefa
        document.getElementById('task-form').addEventListener('submit', (e) => {
            e.preventDefault();
            this.createTask();
        });

        // Botões de status ou exclusão
        document.getElementById('tasks-container').addEventListener('click', (e) => {
            if (e.target.classList.contains('status-btn')) {
                const taskId = parseInt(e.target.getAttribute('data-task-id'));
                const status = e.target.getAttribute('data-status');
                this.updateTaskStatus(taskId, status);
            }

            if (e.target.classList.contains('delete-btn')) {
                const taskId = parseInt(e.target.getAttribute('data-task-id'));
                const task = this.findTaskById(taskId);
                if (task) {
                    const proxy = new TaskProxy(this); // Proxy pattern
                    proxy.deleteTask(task);
                }
            }
        });

        // Aplicação de decoradores
        document.getElementById('apply-decorators').addEventListener('click', () => {
            this.applyDecorators();
        });

        // Observadores
        document.getElementById('screen-observer').addEventListener('change', this.updateObservers.bind(this));
        document.getElementById('email-observer').addEventListener('change', this.updateObservers.bind(this));
        document.getElementById('log-observer').addEventListener('change', this.updateObservers.bind(this));
        document.getElementById('storage-observer').addEventListener('change', this.updateObservers.bind(this));

        // Undo (Command pattern)
        const undoButton = document.getElementById('undo-btn');
        if (undoButton) {
            undoButton.addEventListener('click', () => {
                this.commandManager.undo();
                this.renderTasks();
            });
        }
    }

    createTask() {
        const title = document.getElementById('title').value;
        const description = document.getElementById('description').value;
        const taskType = document.getElementById('task-type').value;

        if (!title) {
            alert('Por favor, insira um título para a tarefa.');
            return;
        }

        const task = this.taskFactory.createTask(taskType, title, description);
        this.tasks.push(task);

        this.renderTasks();
        document.getElementById('task-form').reset();
    }

    updateTaskStatus(taskId, status) {
        const task = this.findTaskById(taskId);
        if (task) {
            const command = new StatusChangeCommand(task, status); // Command pattern
            this.commandManager.executeCommand(command);

            this.taskSubject.notifyObservers(task, status);
            this.renderTasks();
        }
    }

    deleteTask(taskId) {
        const index = this.tasks.findIndex(task => task.getId() === taskId);
        if (index !== -1) {
            this.tasks.splice(index, 1);
            this.renderTasks();
            return true;
        }
        return false;
    }

    applyDecorators() {
        const selectElement = document.getElementById('decorate-task-select');
        const taskId = parseInt(selectElement.value);

        if (!taskId) {
            alert('Por favor, selecione uma tarefa para aplicar os decoradores.');
            return;
        }

        let task = this.findTaskById(taskId);
        if (!task) return;

        if (document.getElementById('high-priority').checked) {
            task = new HighPriorityDecorator(task);
        }

        if (document.getElementById('color-label').checked) {
            const color = document.getElementById('color-select').value;
            task = new ColorLabelDecorator(task, color);
        }

        if (document.getElementById('due-date').checked) {
            const dueDate = document.getElementById('due-date-input').value;
            if (dueDate) {
                task = new DueDateDecorator(task, dueDate);
            }
        }

        if (document.getElementById('decorator-daily').checked) {
            task = new DailyRepeatDecorator(task);
        }

        const index = this.tasks.findIndex(t => t.getId() === taskId);
        if (index !== -1) {
            this.tasks[index] = task;
            this.renderTasks();
        }
    }

    updateObservers() {
        this.taskSubject = new TaskSubject();
        this.initObservers();
    }

    addNotification(notification) {
        this.notifications.push(notification);
        this.renderNotifications();
    }

    renderTasks() {
        const container = document.getElementById('tasks-container');

        if (this.tasks.length === 0) {
            container.innerHTML = `
                <div class="list-group-item text-center text-muted">
                    Nenhuma tarefa criada ainda
                </div>
            `;
            document.getElementById('decorate-task-select').innerHTML = `
                <option value="">Selecione uma tarefa...</option>
            `;
            return;
        }

        container.innerHTML = '';
        let selectOptions = '<option value="">Selecione uma tarefa...</option>';

        this.tasks.forEach(task => {
            container.innerHTML += task.getHtmlRepresentation();
            selectOptions += `<option value="${task.getId()}">${task.getTitle()}</option>`;
        });

        document.getElementById('decorate-task-select').innerHTML = selectOptions;
    }

    renderNotifications() {
        const container = document.getElementById('notifications-container');

        if (this.notifications.length === 0) {
            container.innerHTML = `
                <div class="list-group-item text-center text-muted">
                    Nenhuma notificação ainda
                </div>
            `;
            return;
        }

        container.innerHTML = '';
        const recentNotifications = [...this.notifications].reverse().slice(0, 10);

        recentNotifications.forEach(notification => {
            container.innerHTML += notification.getHtmlRepresentation();
        });
    }

    findTaskById(id) {
        return this.tasks.find(task => task.getId() === id);
    }
}
