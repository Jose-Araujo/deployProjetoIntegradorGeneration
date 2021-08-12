package com.projetoIntegradorGeneration.projetoIntegradorGeneration.service;


import java.nio.charset.Charset;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.apache.commons.codec.binary.Base64;

import com.projetoIntegradorGeneration.projetoIntegradorGeneration.model.UsuarioLogin;
import com.projetoIntegradorGeneration.projetoIntegradorGeneration.model.UsuarioModel;
import com.projetoIntegradorGeneration.projetoIntegradorGeneration.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public UsuarioModel cadastrarUsuario(UsuarioModel usuario) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);
		return usuarioRepository.save(usuario);
	}

	public Optional<UsuarioModel> atualizarUsuario(UsuarioModel usuario) {
		if (usuarioRepository.findById(usuario.getId()).isPresent()) {

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

			String senhaEncoder = encoder.encode(usuario.getSenha());
			usuario.setSenha(senhaEncoder);

			return Optional.of(usuarioRepository.save(usuario));

		} else {

			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!", null);

		}

	}

	public Optional<UsuarioLogin> loginUsuario(Optional<UsuarioLogin> usuarioLogin) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<UsuarioModel> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());

		if (usuario.isPresent()) {
			if (encoder.matches(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {

				String auth = usuarioLogin.get().getUsuario() + ":" + usuarioLogin.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);

				usuarioLogin.get().setToken(authHeader);				
				usuarioLogin.get().setNome(usuario.get().getNomeCompleto());
				usuarioLogin.get().setSenha(usuario.get().getSenha());
				
				return usuarioLogin;
			}
		}
		return null;
	}
}
