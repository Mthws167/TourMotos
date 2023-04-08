package com.backend.model.dto;

import com.backend.model.entity.Parada;
import com.backend.model.entity.PontoInteresse;
import lombok.*;

import java.sql.Timestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RotaDTO{

    private Long id;

    private String pontoPartida;

    private String pontoDestino;

    private Double distancia;

    private Timestamp tempoEstimado;

    private Parada parada;

    private PontoInteresse pontoInteresse;
}