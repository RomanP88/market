package ru.gb.market.order.tempForOpenApiGenerateFiles.controller;

import ru.gb.market.order.tempForOpenApiGenerateFiles.dto.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

/**
 * A delegate to be called by the {@link OrdersApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-07T22:53:55.526326900+03:00[Europe/Moscow]")
public interface OrdersApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /orders : Returns a Order
     *
     * @return OK (status code 200)
     * @see OrdersApi#ordersGet
     */
    default ResponseEntity<List<Order>> ordersGet() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"address\" : \"address\", \"phone\" : \"phone\", \"id\" : 0, \"listOrderItems\" : [ \"{}\", \"{}\" ], \"username\" : \"username\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /orders/{id} : Returns a Order by Id
     *
     * @param id User ID (required)
     * @return OK (status code 200)
     * @see OrdersApi#ordersIdGet
     */
    default ResponseEntity<Order> ordersIdGet(Object id) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"address\" : \"address\", \"phone\" : \"phone\", \"id\" : 0, \"listOrderItems\" : [ \"{}\", \"{}\" ], \"username\" : \"username\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
