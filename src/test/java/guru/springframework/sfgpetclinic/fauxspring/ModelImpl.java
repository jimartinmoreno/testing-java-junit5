package guru.springframework.sfgpetclinic.fauxspring;

import java.util.HashMap;
import java.util.Map;

public class ModelImpl implements Model{
    Map<Object, Object> modelMap = new HashMap<>();

    @Override
    public void addAttribute(String key, Object o) {
        modelMap.put(key, o);
    }

    @Override
    public void addAttribute(Object o) {
        modelMap.put(o, o);
    }

    public Object getAttribute(String key) {
        return modelMap.get(key);
    }

    public Object getAttribute(Object key) {
        return modelMap.get(key);
    }

    @Override
    public String toString() {
        return "$classname{" +
                "modelMap=" + modelMap +
                '}';
    }
}
