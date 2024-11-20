package org.yaroglek.patterns.domain.iterator;

public interface SurveyIterator<T> {
    boolean hasNext();
    T next();
    boolean hasPrev(); // Проверка наличия предыдущего элемента
    T prev(); // Перемещение назад
}
