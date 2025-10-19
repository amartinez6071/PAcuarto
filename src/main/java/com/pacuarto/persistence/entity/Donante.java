package com.pacuarto.persistence.entity;

import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("DONANTE")
public class Donante extends Usuario{

    @OneToMany(mappedBy="donante",fetch=FetchType.LAZY)
    private List<Menu>menus;
}