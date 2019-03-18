package org.kl.bean;

public final class BiItem<T1, T2> {
    private final String firstName;
    private final T1 firstValue;
    private final String secondName;
    private final T2 secondValue;

    public BiItem(String firstName, String secondName) {
        this.firstName  = firstName;
        this.firstValue = null;
        this.secondName  = secondName;
        this.secondValue = null;
    }

    public BiItem(String firstName, T1 firstValue, String secondName, T2 secondValue) {
        this.firstName  = firstName;
        this.firstValue = firstValue;
        this.secondName = secondName;
        this.secondValue = secondValue;
    }

    public String getFirstName()  { return firstName;  }
    public String getSecondName() { return secondName; }

    public T1 getFirstValue()  { return firstValue;  }
    public T2 getSecondValue() { return secondValue; }
}
