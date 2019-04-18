package hsim.simple.security.util;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Object generator.
 */
@Slf4j
public class ObjectGenerator {

    private static Map<Class<?>, Object> instanceMap = new HashMap();
    /**
     * Default model mapper model mapper.
     *
     * @return the model mapper
     */
    public static ModelMapper defaultModelMapper() {
        return new ModelMapper();
    }

    /**
     * Strict model mapper model mapper.
     *
     * @return the model mapper
     */
    public static ModelMapper strictModelMapper() {
        ModelMapper modelMapper = defaultModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

    /**
     * Enable field model mapper model mapper.
     *
     * @param accessLevel the access level
     * @return the model mapper
     */
    public static ModelMapper enableFieldModelMapper(Configuration.AccessLevel accessLevel) {
        ModelMapper modelMapper = strictModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true);
        modelMapper.getConfiguration().setFieldAccessLevel(accessLevel);
        return modelMapper;
    }

    /**
     * Enable field model mapper model mapper.
     *
     * @return the model mapper
     */
    public static ModelMapper enableFieldModelMapper() {
        return enableFieldModelMapper(Configuration.AccessLevel.PRIVATE);
    }


    /**
     * Get t.
     *
     * @param <T>   the type parameter
     * @param cType the c type
     * @return the t
     */
    public synchronized static <T> T get(Class<T> cType) {

        if( instanceMap.get(cType) != null){
            return cType.cast(instanceMap.get(cType));
        }

        try {
            Object obj = cType.newInstance();
            instanceMap.put(cType, obj);
            return cType.cast(obj);
        } catch (InstantiationException | IllegalAccessException e) {
            log.debug(e.getMessage());
        }
        return null;
    }


}
