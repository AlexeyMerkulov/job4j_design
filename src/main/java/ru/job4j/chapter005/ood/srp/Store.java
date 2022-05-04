package ru.job4j.chapter005.ood.srp;

import java.util.List;

/**
 * Классы, реализующие данный интерфейс, будут являться хранилищем объектов и предоставлять API для работы с хранилищем.
 * Метод mappingObjects будет нарушением SRP, т.к. он отвечает за преобразование данных из хранилища, а не за базовые
 * CRUD операции.
 */

public interface Store {

    void add(Object obj);

    void delete(Object obj);

    List<Object> getAllObjects();

    String mappingObjects();
}
