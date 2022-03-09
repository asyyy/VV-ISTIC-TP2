# TCC *vs* LCC

Explain under which circumstances *Tight Class Cohesion* (TCC) and *Loose Class Cohesion* (LCC) metrics produce the same value for a given Java class. Build an example of such as class and include the code below or find one example in an open-source project from Github and include the link to the class below. Could LCC be lower than TCC for any given class? Explain.

## Answer

TCC and LCC have the same value when all methods are directly connected to each other.
```java=
class SingleValue {

    private int value;

    public SingleValue(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    public void setValue(int newValue) {
        this.value = newValue;
    }

    public String toString() {
        return String.valueOf(value);
    }
}
```
Here TCC and LCC = 3/3 = 1

NP = Maximum number of possible connection

NDC = number of direct connections (number of edges in the connection graph)

NID = number of indirect connections

TCC = NDC / NP
LCC = (NDC + NIC) / NP

Sachant que :

NIC >= 0 et NP >= 0

alors

NIC >= 0

NDC + NIC >= NDC

( NDC + NIC ) / NP >= NDC / NP

LCC >= TCC

So LCC can't be lower than TCC


