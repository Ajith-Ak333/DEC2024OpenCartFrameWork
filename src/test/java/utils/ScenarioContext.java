package utils;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext
{

    private final Map<String,Object> datamap = new HashMap<String,Object>();

    public void setContext(String key,Object value)
    {
        datamap.put(key,value);

    }

    public Object getContext(String key)
    {
       return datamap.get(key);
    }
}
