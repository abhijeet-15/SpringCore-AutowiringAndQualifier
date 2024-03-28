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

#### byType
The class of the bean should be same as the type/class of the dependency

```xml
    <bean id="organObject" class="com.abhijeetsingh.Heart" />
    <bean id="human" class="com.abhijeetsingh.Human" autowire="byType">
```

**By constructor**

A matching constructor should be present in the dependent class and it will be
used for initialisation

```xml
    <bean id="organObject" class="com.abhijeetsingh.Heart" />
<!--    <bean id="human" class="com.abhijeetsingh.Human" autowire="byType">-->
    <bean id="human" class="com.abhijeetsingh.Human" autowire="constructor">
```

```java
    public Human(Organ organ) {
        this.organ = organ;
    }
```

## Using Java
- By default, annotations are disabled. Update the ```beans.xml``` to include ** context xsd**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config />
    <bean id="organObject" class="com.abhijeetsingh.Heart" />
    <bean id="jobObject" class="com.abhijeetsingh.Job" />
    <bean id="human" class="com.abhijeetsingh.Human">
    </bean>
</beans>
```

- Autowiring using constructor: In the dependent class's constructor add the annotation **@AutoWired**
```java
    @Autowired
    public Human(Organ organ, Job job) {
        this.organ = organ;
        this.job = job;
    }
```

- Autowiring using Setter: In the dependent class's setter method add the annotation **@AutoWired**
```java
    @Autowired
    public void setOrgan(Organ organ) {
        this.organ = organ;
    }

    @Autowired
    public void setJob(Job job) {
        this.job = job;
    }
```

For setter **@Autowired**, first it tries to resolve byType and then byName. For ex: If there are two beans of the heart configured,
byType resolution will fail due to conflict. Then the byName (id of the bean and the variable name of the dependency) will be used.

- Annotation **@Qualifier** can be used to explicitly specify which bean to use.

```xml
    <bean id="organObjectOne" class="com.abhijeetsingh.Heart" />
    <bean id="organObjectTwo" class="com.abhijeetsingh.Heart" />
    <bean id="jobObject" class="com.abhijeetsingh.Job" />
    <bean id="human" class="com.abhijeetsingh.Human">
```

```java
    @Autowired
    @Qualifier("organObjectOne")
    public void setOrgan(Organ organ) {
        this.organ = organ;
    }
```
- **@AutoWired** and **@Qualifier** annotations can also be added to the variables directly. Interestingly, it does not require us
add a setter method. The id of the bean is matched directly with the variable type and then name. 
- 
```java

    @Autowired
    @Qualifier("organObjectOne")
    private Organ organ;

    @Autowired
    @Qualifier("jobObject")
    private Job job;
```