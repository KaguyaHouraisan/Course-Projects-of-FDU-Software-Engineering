package SELab.utility.response;

import javafx.util.Pair;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @author William Song
 * This class is a automatic generator of response body HashMap
 * Created at 2020-4-27
 */
public class ResponseGenerator {
    public final static String success = "success";

    /**
     * This static method is used to implement the generation
     * It is actually a generic method and receives all kinds of Objects
     *
     * @return null if the keys are not fields of the targetObj
     * @param targetObj the object to generate
     * @param keys the attribute list we need to generate
     * @param keyMapping we can assign a different value to the default key as the final key at hashMap;
     *
     * You can call this function like
     *                    HashMap<String, String> obj =
     *                    ResponseGenerator.generate(user,
     *                 new String[]{"username", "fullname", "password", "email", "institution", "region"},
     *                 new Pair[]{new Pair<>("password", "pwd"), new Pair<>("institution", "i")});
     *
     */

    public static<T>
    HashMap<String, Object>
        generate(
                T targetObj,
                String[] keys,
                Pair[] keyMapping
        ){

        Class objClass = targetObj.getClass();

        HashMap<String, Object> returnMap = new HashMap<>();

        try {
            for(int i = 0; i < keys.length; i++){

                Field declaredField = objClass.getDeclaredField(keys[i]);
                declaredField.setAccessible(true);
                Object value = declaredField.get(targetObj);
                declaredField.setAccessible(false);
                returnMap.put(keys[i], value);
            }
        }catch (IllegalAccessException|NoSuchFieldException iex){ //the field does not exist
            return null;
        }
        if (keyMapping!= null){ //replace all the default keys to the assigned ones
            for(Pair<String, String> pair: keyMapping){
                String replaceKey = pair.getKey();
                if(returnMap.containsKey(replaceKey)){
                    String returnMapValue = (String) returnMap.get(replaceKey);
                    returnMap.remove(replaceKey);
                    returnMap.put(pair.getValue(), returnMapValue);
                }
            }
        }

       return returnMap;
    }

    /*
    用于处理复用生成返回对象的函数
     */
    //处理对象列表生成返回对象
    public static  <T>
    ResponseWrapper<?> injectObjectFromListToResponse(String responsekey, List<T> list, String[] keys, Pair[] keyMapping){
        HashMap<String, Set<HashMap<String, Object>>> body = new HashMap<>();
        body.put(responsekey, ResponseGenerator.listTransForm(list, keys,keyMapping));
        return new ResponseWrapper<>(200, ResponseGenerator.success, body);
    }

    public static  <T>
    Set<HashMap<String, Object>> listTransForm(List<T> list, String[] keys, Pair[] keyMapping){
        Set<HashMap<String, Object>> responseSet = new HashSet<>();
        for (T x: list) {
            HashMap<String, Object> response = ResponseGenerator.generate(x,
                    keys, keyMapping);
            responseSet.add(response);
        }
        return responseSet;
    }

    //处理单个实例对象生成返回对象
    public static  <T>
    ResponseWrapper<?> injectObjectFromObjectToResponse(String responsekey, T object, String[] keys, Pair[] keyMapping){
        HashMap<String, HashMap<String, Object>> body = new HashMap<>();
        HashMap<String, Object> response = ResponseGenerator.generate(object, keys, keyMapping);
        body.put(responsekey, response);
        return new ResponseWrapper<>(200, ResponseGenerator.success, body);
    }
}

