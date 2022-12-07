package ru.gb.market.cart.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.gb.market.api.cart.CartDto;
import ru.gb.market.cart.entities.Cart;


@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CartMapper {

    CartDto mapToDto(Cart cart);
    Cart mapFromDto(CartDto cartDto);
}
