package com.getir.readingGood.util;

import com.getir.readingGood.model.dto.CustomerDTO;
import com.getir.readingGood.model.dto.OrderDTO;
import com.getir.readingGood.model.dto.OrderDetailDTO;
import com.getir.readingGood.model.entity.BookEntity;
import com.getir.readingGood.model.entity.CustomerEntity;
import com.getir.readingGood.model.entity.OrderDetailEntity;
import com.getir.readingGood.model.entity.OrderEntity;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.modelmapper.spi.MappingEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by ozgurokka on 2/9/22 8:29 PM
 */
public class ObjectMapperUtils {

    private static final ModelMapper modelMapper;

    /**
     * Model mapper property setting are specified in the following block.
     * Default property matching strategy is set to Strict see {@link MatchingStrategies}
     * Custom mappings are added using {@link ModelMapper#addMappings(PropertyMap)}
     */
    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        //converter for customer
        TypeMap<OrderDTO, OrderEntity> typeMap = modelMapper.createTypeMap(OrderDTO.class, OrderEntity.class);
        Converter<String, CustomerEntity> customerConverter = ctx -> new CustomerEntity(ctx.getSource());
        typeMap.addMappings(map -> {
            map.using(customerConverter).map(OrderDTO::getCustomerId, OrderEntity::setCustomerEntity);
        });

        //converter for book
        TypeMap<OrderDetailDTO, OrderDetailEntity> typeMapOrderDetail = modelMapper.createTypeMap(OrderDetailDTO.class, OrderDetailEntity.class);
        Converter<String, BookEntity> bookConverter = ctx -> new BookEntity(ctx.getSource());
        typeMapOrderDetail.addMappings(map -> {
            map.using(bookConverter).map(OrderDetailDTO::getBookId, OrderDetailEntity::setBookEntity);
        });

    }


    /**
     * Hide from public usage.
     */
    private ObjectMapperUtils() {
    }

    /**
     * <p>Note: outClass object must have default constructor with no arguments</p>
     *
     * @param <D>      type of result object.
     * @param <T>      type of source object to map from.
     * @param entity   entity that needs to be mapped.
     * @param outClass class of result object.
     * @return new object of <code>outClass</code> type.
     */
    public static <D, T> D map(final T entity, Class<D> outClass) {
        return modelMapper.map(entity, outClass);
    }

    /**
     * <p>Note: outClass object must have default constructor with no arguments</p>
     *
     * @param entityList list of entities that needs to be mapped
     * @param outCLass   class of result list element
     * @param <D>        type of objects in result list
     * @param <T>        type of entity in <code>entityList</code>
     * @return list of mapped object with <code><D></code> type.
     */
    public static <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
        return entityList.stream()
                .map(entity -> map(entity, outCLass))
                .collect(Collectors.toList());
    }

    /**
     * Maps {@code source} to {@code destination}.
     *
     * @param source      object to map from
     * @param destination object to map to
     */
    public static <S, D> D map(final S source, D destination) {
        modelMapper.map(source, destination);
        return destination;
    }

}