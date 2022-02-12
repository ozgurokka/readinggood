package com.getir.readingGood.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by ozgurokka on 2/12/22 10:25 PM
 */
@Entity
@Table(name = "order_detail", schema = "readinggood", catalog = "")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetailEntity {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Basic
    @Column(name = "count", nullable = false)
    private int count;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

    @OneToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private BookEntity bookEntity;
}
