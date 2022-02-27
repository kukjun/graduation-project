package context;

import org.reflections.Reflections;
import spms.annotation.Component;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Set;

public class ApplicationContext {
    Hashtable<String, Object> objTable = new Hashtable<>();

    public Object getBean(String key) {
        return objTable.get(key);
    }

    public ApplicationContext(String propertiesPath) throws Exception {
        // property file loading
        Properties props = new Properties();
        props.load(new FileReader(propertiesPath));

        prepareObject(props);
        prepareAnnotationObjects();
        injectDependency();
    }

    // props 에 있는 객체들을 가져다가 생성하는 메서드
    private void prepareObject(Properties props) throws Exception {
        Context ctx = new InitialContext();
        String key;
        String value;

        for (Object item : props.keySet()) {
            key = (String) item;
            value = props.getProperty(key);
            if (key.startsWith("jndi.")) {
                objTable.put(key, ctx.lookup(value));
            } else {
                objTable.put(key, Class.forName(value).newInstance());
            }
        }
    }

    // Annotation 을 사용하기 위해 필요한 메서드
    private void prepareAnnotationObjects() throws Exception {
        // Reflection 사용
        Reflections reflector = new Reflections("");

        Set<Class<?>> list = reflector.getTypesAnnotatedWith(Component.class);
        String key = null;

        for (Class<?> clazz : list) {
            key = clazz.getAnnotation(Component.class).value();
            objTable.put(key, clazz.newInstance());
        }
    }

    //  의존주입을 해주기 위한 메서드
    private void injectDependency() throws Exception {
        for (String key : objTable.keySet()) {
            if (!key.startsWith("jndi.")) {
                callSetter(objTable.get(key));
            }
        }
    }

    private void callSetter(Object obj) throws Exception {
        Object dependency;
        for (Method m : obj.getClass().getMethods()) {
            if (m.getName().startsWith("set")) {
                dependency = findObjectByType(m.getParameterTypes()[0]);
                if (dependency != null) {
                    m.invoke(obj, dependency);
                }
            }
        }
    }

    private Object findObjectByType(Class<?> type) {
        for (Object obj : objTable.values()) {
            if (type.isInstance(obj)) {
                return obj;
            }
        }
        return null;
    }

}
