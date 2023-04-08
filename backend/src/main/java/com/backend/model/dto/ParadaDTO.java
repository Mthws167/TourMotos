package com.backend.model.dto;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParadaDTO {

    private Long id;

    private String nome;

    private String endereco;

}