/**
 * Singleton para configurações globais da aplicação
 */
class AppSettings {
    constructor() {
        if (AppSettings.instance) {
            return AppSettings.instance;
        }

        this.settings = {
            theme: 'light',
            maxTasks: 50,
            showCompleted: true
        };

        AppSettings.instance = this;
    }

    get(key) {
        return this.settings[key];
    }

    set(key, value) {
        this.settings[key] = value;
    }

    toggle(key) {
        this.settings[key] = !this.settings[key];
    }
}