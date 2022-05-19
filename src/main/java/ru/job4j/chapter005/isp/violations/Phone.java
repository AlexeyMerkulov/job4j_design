package ru.job4j.chapter005.isp.violations;

public interface Phone {
    /**
     * Нарушение принципа ISP заключается в том, что не все телефоны смогут снимать фото
     * или выходить в интернет(например кнопочные).
     * Соответственно придется делать заглушки для двух методов
     */
    void browseInternet();

    void call();

    void takePhoto();
}
