package complex1;

import cabin.operatorSection.OperatorSection;

public class Person {

    IDCard idCard;
    String name;

    public Person(IDCard idCard, String name) {
        this.idCard = idCard;
        this.name = name;
    }

    public Person() {

    }

    public OperatorSection getOperatorSection() {
        return operatorSection;
    }

    private OperatorSection operatorSection;

    public String getName() {
        return name;
    }


}
