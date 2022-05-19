package ru.job4j.chapter005.isp.violations;

public interface House {
    /**
     * Нарушение принципа ISP заключается в том, что не во всех домах может быть установлен лифт,
     * и не каждый дом может быть подключен к системе газоснабжения.
     * Соответственно придется делать заглушки для двух методов
     */
    void setLadder();

    void setElevator();

    void setGas();
}
