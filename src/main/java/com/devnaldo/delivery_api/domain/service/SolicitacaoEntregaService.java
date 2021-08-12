package com.devnaldo.delivery_api.domain.service;

import java.time.OffsetDateTime;

import com.devnaldo.delivery_api.domain.model.Cliente;
import com.devnaldo.delivery_api.domain.model.Entrega;
import com.devnaldo.delivery_api.domain.model.StatusEntrega;
import com.devnaldo.delivery_api.domain.repository.EntregaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

    private CatalogoClienteService catalogoClienteService;
    private EntregaRepository entregaRepository;

    @Transactional
    public Entrega solicitar(Entrega entrega) {
        Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());

        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());

        return entregaRepository.save(entrega);
    }

}