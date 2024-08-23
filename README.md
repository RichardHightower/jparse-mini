
# JParse - Easy JSON Parsing

JParse mini, [fast](https://github.com/nats-io/jparse/wiki#is-jparse-fast), and compliant JSON parser that implements events parsing and index overlay.
It is based on JParse but removes the event parser and function table support.

# Goals

Fastest, most compliant and smallest JSON parser lib for the JVM, and it is developer friendly.

# License

Apache 2.

# Changes
* Got rid of event parser
* Got rid of func table parser 
* We only kept the IndexOverlay parser which is the fastest for the most common use cases. 
* Removed Thread Local and Static members from Json to simplify development, now you have to use a builder.
* There is no longer support for comments 

## Using JParse

This should work with JParse mini but JParse mini is a subset of JParse so your results may vary. 

This is a [short guide on how to use JParse](https://github.com/nats-io/jparse/wiki#using-jparse).
([Go to the wiki for a longer guide on how to use JParse](https://github.com/nats-io/jparse/wiki#using-jparse).)

### Maven

JParse mini has no dependencies except the JVM itself.

TBD

### Gradle

TBD


### Reading JSON from a File

TBD


## Using JParse mini
This has not been updated for JParse mini yet. 

### Extracting Specific Data

Extract employees from the engineering department:

```java

final var engineeringEmployees = Path.atPath("departments[0].employees", rootNode).asCollection().asArray();

```

### Working with JSON Path expressions

Extract specific node from the engineeringEmployees array:

```java 
final var cindy = Path.atPath("[2]", engineeringEmployees);

```

Extract different parts of the cindy node:

```java
final var cindyName = Path.atPath("[2].firstName", engineeringEmployees);
final var cindyId = Path.atPath(".id", cindy);
final var manager = Path.atPath("[2].manager", engineeringEmployees);
```

### API Overview

JParse uses standard Java types, making it straightforward to use with no surprises:

| JSON Type | Java Type |
| --- | --- |
| JSON number | java.lang.Number |
| JSON array | java.util.List |
| JSON array | Java array (e.g., int[]) |
| JSON String and every node | java.lang.CharSequence |
| JSON object | java.util.Map |


### Functional Programming with JParse
JParse supports Java streams and functional programming:

```java 

final var rick = engineeringEmployees.stream()
            .map(node -> node.asCollection().asObject())
            .filter(objectNode -> objectNode.getString("firstName").equals("Rick"))
            .findFirst();

```
You can also use JParse for functional mapping and find operations:

```java

final var rick2 = engineeringEmployees.findObjectNode(
objectNode -> objectNode.getString("firstName").equals("Rick")
);
```


### Object Mappings
JParse supports easy object mappings:

```java 
public record Employee(String firstName, String lastName,
String dob, boolean manager,
int id, int managerId) {
}
```

```java
final var employees = engineeringEmployees.mapObjectNode(on ->
    new Employee(on.getString("firstName"), on.getString("lastName"),
        on.getString("dob"), on.getBoolean("manager"),
        on.getInt("id"), on.getInt("managerId"))
);

```
