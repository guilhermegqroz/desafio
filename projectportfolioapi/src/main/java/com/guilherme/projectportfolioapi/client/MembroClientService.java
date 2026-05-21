package com.guilherme.projectportfolioapi.client;
import com.guilherme.projectportfolioapi.dto.response.MembroResponseDTO;
import com.guilherme.projectportfolioapi.exception.NegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class MembroClientService {

    private final RestTemplate restTemplate;

	@Value("${membro.api.url}")
	private String membroApiUrl;

	public MembroResponseDTO buscarMembro(String membroId) {

		String url = String.format("%s/%s", membroApiUrl, membroId);
		try {
			return restTemplate.getForObject(url, MembroResponseDTO.class);
		} catch (RestClientException ex) {
			throw new NegocioException(
					"Erro ao buscar membro na API externa."
			);
		}
	}
}