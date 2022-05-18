package ru.job4j.chapter005.lsp.violations;

public class Elevator {
    public String checkPassengersWeight(int numberOfPeople, double avgWeight) {
        double totalWeight = numberOfPeople * avgWeight;
        if (totalWeight > 400) {
            throw new IllegalArgumentException("Overloading");
        }
        return "OK";
    }
}

class ServiceElevator extends Elevator {
    /**
     * В переопределенном методе класса ServiceElevator ослаблено постусловие, что является нарушением контракта.
     */
    @Override
    public String checkPassengersWeight(int numberOfPeople, double avgWeight) {
        double totalWeight = numberOfPeople * avgWeight;
        if (totalWeight > 800) {
            throw new IllegalArgumentException("Overloading");
        }
        return "OK";
    }
}
