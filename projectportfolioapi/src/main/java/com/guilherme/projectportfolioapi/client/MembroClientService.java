package com.guilherme.projectportfolioapi.client;
import com.guilherme.projectportfolioapi.dto.response.MembroResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MembroClientService {
	
    private final RestTemplate restTemplate;

	@Value("${membro.api.url}")
	private String membroApiUrl;
	
    public MembroClientService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
    public MembroResponseDTO buscarMembro(String membroId) {
		return restTemplate.getForObject(membroApiUrl + "/" + membroId, MembroResponseDTO.class);
	}
}