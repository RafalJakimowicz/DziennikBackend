package org.example.dziennikbackend.utils;
import org.modelmapper.ModelMapper;

public class DTOMapper {
    private static final ModelMapper mapper = new ModelMapper();

    /**
     * Maps fields from source object to target object
     * @param source object to get data from
     * @param destination object that will get data
     * @return target class with fields mapped from source class
     * @param <S> type of source object
     * @param <T> type of target object
     */
    public static <S, T> T map(S source, Class<T> destination){
        return mapper.map(source, destination);
    }
}
