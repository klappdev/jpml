package org.kl.bean;

public final class TriItem<T1, T2, T3> {
    private final String firstName;
    private final T1 firstValue;
    private final String secondName;
    private final T2 secondValue;
    private final String thirdName;
    private final T3 thirdValue;

    public TriItem(String firstName, String secondName, String thirdName) {
        this.firstName   = firstName;
        this.firstValue  = null;
        this.secondName  = secondName;
        this.secondValue = null;
        this.thirdName   = thirdName;
        this.thirdValue  = null;
    }

    public TriItem(String firstName, T1 firstValue, String secondName, T2 secondValue,
                   String thirdName, T3 thirdValue) {
        this.firstName   = firstName;
        this.firstValue  = firstValue;
        this.secondName  = secondName;
        this.secondValue = secondValue;
        this.thirdName   = thirdName;
        this.thirdValue  = thirdValue;
    }

    public String getFirstName()  { return firstName;  }
    public String getSecondName() { return secondName; }
    public String getThirdName()  { return thirdName;  }

    public T1 getFirstValue()  { return firstValue;  }
    public T2 getSecondValue() { return secondValue; }
    public T3 getThirdValue()  { return thirdValue;  }
}