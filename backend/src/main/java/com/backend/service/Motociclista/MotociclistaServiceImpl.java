package com.backend.service.Motociclista;

import com.backend.entity.Motociclista;
import com.backend.exception.InfoException;
import com.backend.repository.MotociclistaRepository;
import com.backend.utils.UtilsMotociclista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotociclistaServiceImpl implements MotociclistaService {
    @Autowired
    private MotociclistaRepository motociclistaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Motociclista> buscarTodos() {
        return motociclistaRepository.findAll();
    }

    @Override
    public Motociclista buscaPerfil(Long id) throws InfoException {
        Optional<Motociclista> motociclistaOptional = motociclistaRepository.findById(id);
        if (motociclistaOptional.isPresent()) {
            return motociclistaOptional.get();
        } else {
            throw new InfoException("Motociclista não encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Motociclista buscaPerfilComLogin(String email, String senha) throws InfoException {
        Motociclista motociclistaOptional = motociclistaRepository.findMotociclistaByEmail(email);
        if (passwordEncoder.matches(senha, motociclistaOptional.getSenha())) {
            if (motociclistaOptional != null) {
                return motociclistaOptional;
            } else {
                throw new InfoException("Motociclista não encontrado: " + motociclistaOptional.getNome(), HttpStatus.NOT_FOUND);
            }
        }else{
            throw new InfoException("Senha incorreta", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Motociclista inserir(Motociclista motociclista) throws InfoException {
        if (UtilsMotociclista.validarMotociclista(motociclista)) {
            if (UtilsMotociclista.validarEmail(motociclista.getEmail())) {
                if (motociclistaRepository.findMotociclistaByEmail(motociclista.getEmail()) == null) {
                    if (UtilsMotociclista.validarCPF(motociclista.getCpf())) {
                        if (motociclistaRepository.findByCpf(motociclista.getCpf()).isEmpty()) {
                            motociclista.setSenha(passwordEncoder.encode(motociclista.getSenha()));
                            return motociclistaRepository.save(motociclista);
                        }
                        throw new InfoException("Usuário já cadastrado", HttpStatus.BAD_REQUEST);
                    }
                    throw new InfoException("CPF inválido", HttpStatus.BAD_REQUEST);
                }
                throw new InfoException("Usuário já cadastrado", HttpStatus.BAD_REQUEST);
            }
            throw new InfoException("E-mail inválido", HttpStatus.BAD_REQUEST);
        }
        throw new InfoException("Ocorreu um erro ao cadastrar motociclista", HttpStatus.BAD_REQUEST);
    }

    @Override
    public Motociclista alterar(Long id, Motociclista motociclista) throws InfoException {
        Optional<Motociclista> motociclistaOptional = motociclistaRepository.findById(id);
        if (motociclistaOptional.isPresent()) {
            if (UtilsMotociclista.validarEmail(motociclista.getEmail())) {
                motociclistaOptional.get().setNome(motociclista.getNome());
                motociclistaOptional.get().setEmail(motociclista.getEmail());
                if(motociclista.getMoto() != null){
                    motociclistaOptional.get().setMoto(motociclista.getMoto());
                }else{
                    motociclistaOptional.get().setMoto(null);
                }
                motociclistaRepository.save(motociclistaOptional.get());
                return motociclistaOptional.get();
            } else {
                throw new InfoException("E-mail inválido", HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new InfoException("Motociclista não encontrado", HttpStatus.NOT_FOUND);
        }
    }


    @Override
    public void excluir(Long id) throws InfoException {
        Optional<Motociclista> motociclista = motociclistaRepository.findById(id);

        if (motociclista.isPresent()) {
            motociclistaRepository.delete(motociclista.get());
        } else {
            throw new InfoException("Motociclista não encontrado", HttpStatus.NOT_FOUND);
        }
    }
}
