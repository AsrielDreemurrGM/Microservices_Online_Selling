package br.com.eaugusto.onlineselling.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utility class to handle REST calls
 * 
 * @author Eduardo Augusto
 * @since Aug 14, 2025
 */
@Component
public class RestUtils {

	private static final Logger logger = LoggerFactory.getLogger(RestUtils.class);

	private final RestTemplate restTemplate;
	private final ObjectMapper objectMapper;

	public RestUtils(RestTemplate restTemplate, ObjectMapper objectMapper) {
		this.restTemplate = restTemplate;
		this.objectMapper = objectMapper;
	}

	public <T> ResponseEntity<T> execute(String url, RestRequest request, Class<T> responseType,
			Object... uriVariables) {

		logCall(request.getMethod(), url, request.getHttpEntity(), uriVariables);

		long start = System.currentTimeMillis();
		try {
			return restTemplate.exchange(url, request.getMethod(), request.getHttpEntity(), responseType, uriVariables);
		} catch (Exception e) {
			logError(request.getMethod(), url, e);
			throw e;
		} finally {
			logElapsed(request.getMethod(), url, start);
		}
	}

	private void logCall(HttpMethod method, String url, HttpEntity<?> httpEntity, Object[] uriVariables) {
		HttpHeaders headers = null;
		Object body = null;
		if (httpEntity != null) {
			headers = httpEntity.getHeaders();
			body = httpEntity.getBody();
		}

		logger.debug("EXECUTING ENDPOINT WITH {} ON URL: {}, PARAMETERS: {}, HEADERS: {} AND BODY: {}", method, url,
				uriVariables, headers != null ? asJSON(headers) : null, body != null ? asJSON(body) : null);
	}

	private void logError(HttpMethod method, String url, Exception e) {
		logger.error("ERROR ON ENDPOINT WITH {} ON URL: {} - {}", method, url, e.getMessage());
	}

	private void logElapsed(HttpMethod method, String url, long start) {
		logger.debug("ELAPSED TIME ON ENDPOINT WITH {} ON URL: {} - {} ms", method, url,
				System.currentTimeMillis() - start);
	}

	protected String asJSON(Object dto) {
		if (dto == null) {
			return null;
		}
		try {
			return objectMapper.writeValueAsString(dto);
		} catch (JsonProcessingException e) {
			logger.error("ERROR CONVERTING OBJECT TO JSON");
			return null;
		}
	}
}
