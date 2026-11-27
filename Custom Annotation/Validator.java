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
