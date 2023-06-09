package com.backend.dto;

import com.backend.entity.Rota;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MotociclistaDTO {

    private Long id;

    private String nome;

    private String cpf;

    private String email;

    private String senha;

    private Rota rota;
}