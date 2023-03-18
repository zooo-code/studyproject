package study.project.domain.delivery;

import lombok.Getter;
import study.project.domain.address.Address;
import study.project.domain.order.Order;

import javax.persistence.*;

@Entity
@Getter
public class Delivery {


    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
}
