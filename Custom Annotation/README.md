# CampusBoot — Mini IoC, Validation & Routing Framework in Java

**CampusBoot** is a lightweight, zero-dependency Java framework built from scratch using **Custom Annotations** and the **Java Reflection API**. Inspired by Spring Boot, it demonstrates how Inversion of Control (IoC), configuration value injection, declarative validation, and URL routing work under the hood without any external libraries.

---

## 📋 Table of Contents
1. [Project Overview](#project-overview)
2. [Features & Architecture](#features--architecture)
3. [Custom Annotations Specification](#custom-annotations-specification)
4. [Complete Source Code](#complete-source-code)
   - [Part A: Custom Annotations](#part-a-custom-annotations)
   - [Part B: IoC Container & Value Injector](#part-b-ioc-container--value-injector)
   - [Part C: Declarative Validator](#part-c-declarative-validator)
   - [Part D: URL Router](#part-d-url-router)
   - [Application & Starter Classes](#application--starter-classes)
5. [How to Compile & Run](#how-to-compile--run)
6. [Complete Console Output](#complete-console-output)
7. [Part E: Reflection Questions & Answers](#part-e-reflection-questions--answers)

---

## 📖 Project Overview

In enterprise Java development, frameworks like Spring Boot eliminate repetitive boilerplate code—such as manually creating objects, parsing configuration properties, validating fields, and mapping URLs to controller methods.

**CampusBoot** implements these four core mechanisms in pure **Java 11+**, showing that annotations are passive metadata markers that only perform work when inspected and executed by a reflection engine at runtime.

---

## ⚙️ Features & Architecture

1. **Inversion of Control (IoC) Container (`Container.java`):**
   - Scans provided classes for `@Component`.
   - Instantiates managed beans dynamically via their no-argument constructors (`getDeclaredConstructor().newInstance()`).
   - Resolves bean names either from the explicit `@Component("name")` element or converts the class's simple name to `lowerCamelCase`.
   - Safely skips classes without `@Component`.

2. **Configuration Value Injection (`Container.java`):**
   - Inspects fields annotated with `@Value("config.key")` across all registered beans.
   - Reads key-value pairs from a `Map<String, String>` configuration source.
   - Uses `field.setAccessible(true)` to bypass `private` access modifiers and converts raw strings into `String` or `int`/`Integer` types.

3. **Declarative Field Validation (`Validator.java`):**
   - Inspects an object's declared fields for `@Range(min, max, message)`.
   - Checks whether `int` values fall within the inclusive `[min, max]` range.
   - Collects violation messages formatted strictly as: `fieldName - message (actual: value)`.

4. **Annotation-Based Routing (`Router.java`):**
   - Scans all beans stored inside the `Container` for methods annotated with `@Route("/path")`.
   - Matches incoming request paths, invokes the target method dynamically (`method.invoke(bean)`), and returns the `String` result.
   - Returns `404 Not Found: <path>` when no matching route exists.

---

## 🏷️ Custom Annotations Specification

All four annotations are marked with `@Retention(RetentionPolicy.RUNTIME)` so the JVM keeps their metadata available for reflection at runtime.

| Annotation | `@Target` | Elements & Defaults | Purpose |
| :--- | :--- | :--- | :--- |
| `@Component` | `ElementType.TYPE` | `String value() default ""` | Marks a class to be created and managed by the container. |
| `@Value` | `ElementType.FIELD` | `String value()` | Injects a configuration value into a field. |
| `@Range` | `ElementType.FIELD` | `int min()`, `int max()`, `String message() default "Value out of range"` | Validates that an `int` field is within `[min, max]`. |
| `@Route` | `ElementType.METHOD` | `String path()` | Maps a URL path to a controller method. |

---

## 💻 Complete Source Code

### Part A: Custom Annotations

#### `Component.java`
```java
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Component {
    String value() default "";
}
```

#### `Value.java`
```java
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Value {
    String value();
}
```

#### `Range.java`
```java
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Range {
    int min();
    int max();
    String message() default "Value out of range";
}
```

#### `Route.java`
```java
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Route {
    String path();
}
```

---

### Part B: IoC Container & Value Injector

#### `Container.java`
```java
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Container {
    private final Map<String, Object> beans = new LinkedHashMap<>();

    public void scan(Class<?>... classes) {
        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(Component.class)) {
                Component component = clazz.getAnnotation(Component.class);
                String beanName = component.value();
                if (beanName == null || beanName.isEmpty()) {
                    String simpleName = clazz.getSimpleName();
                    beanName = Character.toLowerCase(simpleName.charAt(0)) + simpleName.substring(1);
                }
                try {
                    Object instance = clazz.getDeclaredConstructor().newInstance();
                    beans.put(beanName, instance);
                    System.out.println("[Container] Registered: " + beanName + " (" + clazz.getSimpleName() + ")");
                } catch (Exception e) {
                    throw new RuntimeException("Failed to instantiate bean: " + clazz.getName(), e);
                }
            } else {
                System.out.println("[Container] Skipped: " + clazz.getSimpleName() + " (no @Component)");
            }
        }
    }

    public void injectValues(Map<String, String> config) {
        for (Object bean : beans.values()) {
            Class<?> clazz = bean.getClass();
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Value.class)) {
                    Value valueAnnotation = field.getAnnotation(Value.class);
                    String key = valueAnnotation.value();
                    if (config.containsKey(key)) {
                        String rawValue = config.get(key);
                        field.setAccessible(true);
                        try {
                            Class<?> fieldType = field.getType();
                            if (fieldType == String.class) {
                                field.set(bean, rawValue);
                            } else if (fieldType == int.class || fieldType == Integer.class) {
                                field.set(bean, Integer.parseInt(rawValue));
                            } else {
                                throw new IllegalArgumentException("Unsupported field type for @Value: " + fieldType.getName());
                            }
                            System.out.println("[Container] Injected: " + key + " -> " + clazz.getSimpleName() + "." + field.getName());
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException("Failed to inject value into field: " + field.getName(), e);
                        }
                    }
                }
            }
        }
    }

    public Object getBean(String name) {
        return beans.get(name);
    }

    public Collection<Object> getAllBeans() {
        return beans.values();
    }
}
```

---

### Part C: Declarative Validator

#### `Validator.java`
```java
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Validator {
    public static List<String> validate(Object obj) {
        List<String> errors = new ArrayList<>();
        if (obj == null) {
            return errors;
        }

        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Range.class)) {
                Range range = field.getAnnotation(Range.class);
                field.setAccessible(true);
                try {
                    Object val = field.get(obj);
                    if (val instanceof Integer) {
                        int intVal = (Integer) val;
                        if (intVal < range.min() || intVal > range.max()) {
                            errors.add(field.getName() + " - " + range.message() + " (actual: " + intVal + ")");
                        }
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Failed to access field for validation: " + field.getName(), e);
                }
            }
        }
        return errors;
    }
}
```

---

### Part D: URL Router

#### `Router.java`
```java
import java.lang.reflect.Method;

public class Router {
    public static String dispatch(Container c, String path) {
        for (Object bean : c.getAllBeans()) {
            Class<?> clazz = bean.getClass();
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Route.class)) {
                    Route route = method.getAnnotation(Route.class);
                    if (route.path().equals(path)) {
                        method.setAccessible(true);
                        try {
                            Object result = method.invoke(bean);
                            return result != null ? result.toString() : "";
                        } catch (Exception e) {
                            throw new RuntimeException("Failed to invoke route method: " + method.getName(), e);
                        }
                    }
                }
            }
        }
        return "404 Not Found: " + path;
    }
}
```

---

### Application & Starter Classes

#### `GradeController.java`
```java
@Component("gradeController")
public class GradeController {
    @Value("app.name")     private String appName;
    @Value("app.passMark") private int passMark;

    @Route("/hello")
    public String hello() { return "Welcome to " + appName; }

    @Route("/status")
    public String status() { return "Pass mark is " + passMark; }
}
```

#### `ReportPrinter.java`
```java
public class ReportPrinter { }
```

#### `Student.java`
```java
public class Student {
    @Range(min = 0, max = 100, message = "Score must be between 0 and 100")
    private int score;

    @Range(min = 1, max = 12)
    private int semester;

    public Student(int score, int semester) { this.score = score; this.semester = semester; }
}
```

#### `Main.java`
```java
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String,String> config = new HashMap<>();
        config.put("app.name", "CampusBoot");
        config.put("app.passMark", "40");

        Container container = new Container();
        container.scan(GradeController.class, ReportPrinter.class);
        container.injectValues(config);

        System.out.println("--- Validation ---");
        for (Student s : List.of(new Student(120, 5), new Student(85, 5), new Student(70, 15))) {
            List<String> errors = Validator.validate(s);
            if (errors.isEmpty()) System.out.println("OK");
            else errors.forEach(e -> System.out.println("VIOLATION: " + e));
        }

        System.out.println("--- Routing ---");
        for (String p : new String[]{"/hello", "/status", "/unknown"})
            System.out.println("[Router] " + p + " -> " + Router.dispatch(container, p));
    }
}
```

---

## 🚀 How to Compile & Run

1. **Prerequisites:** Java Development Kit (**JDK 11+**) installed (no external libraries required).
2. **Compile all Java source files:**
   ```bash
   javac *.java
   ```
3. **Execute the application:**
   ```bash
   java Main
   ```

---

## 🧪 Complete Console Output

Running `java Main` outputs the following exact result:

```text
[Container] Registered: gradeController (GradeController)
[Container] Skipped: ReportPrinter (no @Component)
[Container] Injected: app.name -> GradeController.appName
[Container] Injected: app.passMark -> GradeController.passMark
--- Validation ---
VIOLATION: score - Score must be between 0 and 100 (actual: 120)
OK
VIOLATION: semester - Value out of range (actual: 15)
--- Routing ---
[Router] /hello -> Welcome to CampusBoot
[Router] /status -> Pass mark is 40
[Router] /unknown -> 404 Not Found: /unknown
```

---

## 💡 Part E: Reflection Questions & Answers

### 1. What would happen to your framework if you forgot `@Retention(RetentionPolicy.RUNTIME)`? Why?
If `@Retention(RetentionPolicy.RUNTIME)` is omitted, Java defaults to `RetentionPolicy.CLASS`, which records annotations in the compiled `.class` bytecode file but discards them when the JVM loads the class at runtime. Consequently, reflection calls such as `isAnnotationPresent()` and `getAnnotation()` would always return `false` or `null`, causing CampusBoot to skip every component, skip value injections, ignore validation rules, and fail all route lookups with 404 errors.

### 2. Map each CampusBoot annotation to its Spring Boot equivalent and name one thing real Spring does that your mini-framework does not.
In Spring Boot, `@Component` maps to `@Component` (or `@RestController` for web controllers), `@Value` maps to `@Value`, `@Range` maps to Jakarta Bean Validation's `@Range` / `@Min` and `@Max` (enforced via `@Valid`), and `@Route` maps to `@GetMapping` or `@RequestMapping`. Unlike CampusBoot, real Spring performs automatic classpath scanning across packages, resolves dependency graphs via constructor/field `@Autowired` injection, creates dynamic AOP proxies (for transactions and security), and embeds a full HTTP web server like Tomcat to handle real network requests, query parameters, and JSON serialization.

### 3. Why is `setAccessible(true)` both powerful and risky?
`setAccessible(true)` is powerful because it lets frameworks inspect and inject values into `private` fields or invoke non-public methods at runtime, allowing developers to keep fields cleanly encapsulated without writing boilerplate getters and setters. However, it is risky because it bypasses Java's compile-time access control and encapsulation boundaries, which can mutate internal object invariants unexpectedly and trigger security/module restrictions (such as `InaccessibleObjectException` under the Java Platform Module System).