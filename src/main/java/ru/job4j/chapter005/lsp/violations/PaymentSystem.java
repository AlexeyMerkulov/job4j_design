package ru.job4j.chapter005.lsp.violations;

public class PaymentSystem {
    public void acceptPayment(double amountOfMoney) {
        if (amountOfMoney < 0) {
            throw new IllegalArgumentException("Not possible to accept the payment");
        }
        System.out.println("Payment is accepted");
    }
}

class SmallPaymentSystem extends PaymentSystem {
    /**
     * В переопределенном методе класса SmallPaymentSystem усилено предусловие, что является нарушением контракта.
     */
    @Override
    public void acceptPayment(double amountOfMoney) {
        if (amountOfMoney < 0 || amountOfMoney > 100000) {
            throw new IllegalArgumentException("Not possible to accept the payment");
        }
        System.out.println("Payment is accepted");
    }
}
