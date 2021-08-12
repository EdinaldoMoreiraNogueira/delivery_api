package com.devnaldo.delivery_api.domain.service;
import com.devnaldo.delivery_api.domain.exception.NegocioException;
import com.devnaldo.delivery_api.domain.model.Cliente;
import com.devnaldo.delivery_api.domain.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.*;


import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CatalogoClienteService {

    private ClienteRepository clienteRepository;

    public Cliente buscar(Long clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new NegocioException("Cliente não encontrado"));
    }

    @Transactional
    public Cliente salvar(Cliente cliente) {
        boolean emailEmUso;
        if (clienteRepository.findByEmail(cliente.getEmail())
                .stream()
                .noneMatch(clienteExistente -> !clienteExistente.equals(cliente))) {
            emailEmUso = false;
        } else {
            emailEmUso = true;
        }

        if (emailEmUso) {
            throw new NegocioException("Já existe um cliente cadastrado com este e-mail.");
        }

        return clienteRepository.save(cliente);
    }

    @Transactional
    public void excluir(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }

}