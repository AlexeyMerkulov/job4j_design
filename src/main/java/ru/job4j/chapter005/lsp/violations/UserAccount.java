package ru.job4j.chapter005.lsp.violations;

public class UserAccount {
    protected String login;
    protected String password;

    public UserAccount(String login, String password) {
        validatePassword(password);
        this.login = login;
        this.password = password;
    }

    protected void validatePassword(String password) {
        if (password.length() < 8) {
            throw new IllegalArgumentException("Incorrect length!");
        }
    }

    public void setPassword(String password) {
        validatePassword(password);
        this.password = password;
    }
}

class EmailUserAccount extends UserAccount {
    /**
     * Инвариант нарушен. У класса-потомка отсутствует валидация пароля в сеттере
     */
    public EmailUserAccount(String login, String password) {
        super(login, password);
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
}
