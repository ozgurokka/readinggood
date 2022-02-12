package com.getir.readingGood.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

/**
 * Created by ozgurokka on 2/12/22 10:25 PM
 */
@Entity
@Table(name = "orders", schema = "readinggood", catalog = "")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Basic
    @Column(name = "amount", nullable = false, precision = 2)
    private BigDecimal amount;
    @Basic
    @Column(name = "order_time", nullable = false)
    private Timestamp orderTime;

    @Basic
    @Column(name = "status", nullable = false)
    private int status;

    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private CustomerEntity customerEntity;

    @JsonManagedReference
    @OneToMany(mappedBy = "orderEntity",cascade = CascadeType.ALL)
    private List<OrderDetailEntity> orderDetailEntityList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return id == that.id &&
                status == that.status &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(orderTime, that.orderTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, orderTime, status);
    }

}
