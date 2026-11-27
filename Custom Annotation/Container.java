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
