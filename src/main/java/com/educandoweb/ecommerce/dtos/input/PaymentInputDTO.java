package com.educandoweb.ecommerce.dtos.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentInputDTO {
    private Long orderId;
    private String moment;
}
