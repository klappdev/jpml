package org.kl.bean;

public final class QuarItem<T1, T2, T3, T4> {
    private final String firstName;
    private final T1 firstValue;
    private final String secondName;
    private final T2 secondValue;
    private final String thirdName;
    private final T3 thirdValue;
    private final String fourthName;
    private final T4 fourthValue;

    public QuarItem(String firstName, String secondName, String thirdName, String fourthName) {
        this.firstName   = firstName;
        this.firstValue  = null;
        this.secondName  = secondName;
        this.secondValue = null;
        this.thirdName   = thirdName;
        this.thirdValue  = null;
        this.fourthName  = fourthName;
        this.fourthValue = null;
    }

    public QuarItem(String firstName, T1 firstValue, String secondName, T2 secondValue,
                    String thirdName, T3 thirdValue, String fourthName, T4 fourthValue) {
        this.firstName   = firstName;
        this.firstValue  = firstValue;
        this.secondName  = secondName;
        this.secondValue = secondValue;
        this.thirdName   = thirdName;
        this.thirdValue  = thirdValue;
        this.fourthName  = fourthName;
        this.fourthValue = fourthValue;
    }

    public String getFirstName()  { return firstName;  }
    public String getSecondName() { return secondName; }
    public String getThirdName()  { return thirdName;  }
    public String getFourthName() { return fourthName; }

    public T1 getFirstValue()  { return firstValue;  }
    public T2 getSecondValue() { return secondValue; }
    public T3 getThirdValue()  { return thirdValue;  }
    public T4 getFourthValue() { return fourthValue; }
}