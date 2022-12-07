package ru.gb.market.order.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.gb.market.api.order.OrderDto;
import ru.gb.market.order.entities.Order;


@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OrderMapper {

    OrderDto mapToDto(Order order);
    Order mapFromDto(OrderDto orderDto);

}
