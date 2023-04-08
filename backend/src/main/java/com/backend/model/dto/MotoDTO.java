package com.backend.model.dto;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MotoDTO {

    private Long id;

    private String modelo;

    private String marca;

}