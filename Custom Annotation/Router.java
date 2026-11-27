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
