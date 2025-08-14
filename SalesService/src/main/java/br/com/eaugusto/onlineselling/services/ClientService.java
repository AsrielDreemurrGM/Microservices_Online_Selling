package br.com.eaugusto.onlineselling.services;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 14, 2025
 */
@Service
public class ClientService {

	@Value("${application.clientService.searchClientEndpoint}")
	private String searchClientEndpointUrl;

	private RestUtils restUtils;

	public ClientService(RestUtils restUtils) {
		this.restUtils = restUtils;
	}

	public Boolean isClientRegistered(String clientId) {
		RestRequest restRequest = new RestRequest(HttpMethod.GET, null);
		restRequest.setContentType(MediaType.APPLICATION_JSON);
		restRequest.setAcceptable(Collections.singletonList(MediaType.APPLICATION_JSON));
		String urlComParam = searchClientEndpointUrl.replace("{id}", clientId);
		ResponseEntity<Boolean> response = restUtils.execute(urlComParam, restRequest, Boolean.class);
		return response.getBody();
	}
}
