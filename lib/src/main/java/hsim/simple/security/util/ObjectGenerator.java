package hsim.simple.security.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

public class ObjectGenerator {

    public static ModelMapper defaultModelMapper() {
        return new ModelMapper();
    }

    public static ModelMapper strictModelMapper() {
        ModelMapper modelMapper = defaultModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

    public static ModelMapper enableFieldModelMapper(Configuration.AccessLevel accessLevel) {
        ModelMapper modelMapper = strictModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true);
        modelMapper.getConfiguration().setFieldAccessLevel(accessLevel);
        return modelMapper;
    }

    public static ModelMapper enableFieldModelMapper() {
        return enableFieldModelMapper(Configuration.AccessLevel.PRIVATE);
    }


}
