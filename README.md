## Autowiring in Spring Framework

In Spring Framework, autowiring is a feature that allows automatic dependency injection. Instead of explicitly specifying dependencies through `<property>` tags, autowiring can be used to let Spring automatically resolve and inject dependencies based on certain rules.

### Autowiring Modes

Autowiring can be configured with different modes:

- **byName**: The id of the bean should match the variable name in the dependent class.
- **byType**: The type of the bean should match the type of the variable in the dependent class.
- **constructor**: Constructor arguments are autowired.

### Example

Consider the following example:

#### Human.java

```java
package com.abhijeetsingh;

public class Human {
    private Organ organ;

    public Human() {}

    public void setOrgan(Organ organ) {
        this.organ = organ;
    }

    public void isAlive() {
        organ.working();
    }
}
```

**Organ.java**
```java
package com.abhijeetsingh;

public interface Organ {
    void working();
}
```

**Heart.java**

```java
package com.abhijeetsingh;

public class Heart implements Organ {
    @Override
    public void working() {
        System.out.println("The heart is pumping");
    }
}
```

**beans.xml**

```xml
<bean id="organ" class="com.abhijeetsingh.Heart" />
<bean id="human" class="com.abhijeetsingh.Human" autowire="byName" />
```