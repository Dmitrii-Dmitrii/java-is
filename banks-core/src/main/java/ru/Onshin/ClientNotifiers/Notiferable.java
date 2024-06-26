package ru.Onshin.ClientNotifiers;

/**
 * Интерфейс представляет собой оповещение, которое может быть отправлено клиенту.
 */
public interface Notiferable {
    /**
     * Уведомляет клиента об изменении условий по счетам
     *
     * @param context уведомительное сообщение для клиента
     */
    void notifyClient(String context);
}
