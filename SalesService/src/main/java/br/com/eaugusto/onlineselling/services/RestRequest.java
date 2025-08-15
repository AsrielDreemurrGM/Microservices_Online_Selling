package br.com.eaugusto.onlineselling.services;

import java.util.List;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Represents an HTTP request for REST communication. Supports setting headers,
 * content type, acceptable media types, bearer token, and body content. Also
 * supports multipart/form-data requests including files or images. Provides a
 * method to return a fully constructed HttpEntity for execution.
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since Aug 14, 2025
 */
public class RestRequest {

	private final HttpHeaders headers;
	private HttpEntity<Object> bodyEntity;
	private final MultiValueMap<String, Object> bodyMap;
	private final HttpMethod method;

	public enum DispositionType {
		INLINE("inline"), ATTACHMENT("attachment"), FORM_DATA("form-data");

		private final String type;

		DispositionType(String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}
	}

	public RestRequest(HttpMethod method, @Nullable Object body) {
		this.method = method;
		this.headers = new HttpHeaders();
		this.bodyMap = new LinkedMultiValueMap<>();
		if (body != null) {
			this.bodyEntity = new HttpEntity<>(body, headers);
		}
	}

	public HttpMethod getMethod() {
		return method;
	}

	public void setContentType(@Nullable MediaType mediaType) {
		this.headers.setContentType(mediaType);
	}

	public void setAcceptable(List<MediaType> acceptableMediaTypes) {
		this.headers.setAccept(acceptableMediaTypes);
	}

	public void setBearerToken(@Nullable String token) {
		this.headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + token);
	}

	public void addHeader(@NonNull String headerName, @Nullable String headerValue) {
		this.headers.set(headerName, headerValue);
	}

	public void addMultiValueMap(String parameterName, Object value) {
		this.bodyMap.add(parameterName, value);
	}

	public void addMultiValueMap(DispositionType dispositionType, String parameterName, Object value) {
		MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
		ContentDisposition contentDisposition = ContentDisposition.builder(dispositionType.getType())
				.name(parameterName).build();
		fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
		HttpEntity<Object> fileEntity = new HttpEntity<>(value, fileMap);
		bodyMap.add(parameterName, fileEntity);
	}

	public void addImageOrFileMultiValueMap(DispositionType dispositionType, String parameterName, String fileName,
			Object value) {
		MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
		ContentDisposition contentDisposition = ContentDisposition.builder(dispositionType.getType())
				.name(parameterName).filename(fileName).build();
		fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
		HttpEntity<Object> fileEntity = new HttpEntity<>(value, fileMap);
		bodyMap.add(parameterName, fileEntity);
	}

	public HttpHeaders getHeaders() {
		return headers;
	}

	public MultiValueMap<String, Object> getBodyMap() {
		return bodyMap;
	}

	/**
	 * Always returns a typed HttpEntity<Object>. If a multi-value map exists, wraps
	 * it into the entity.
	 */
	public HttpEntity<Object> getHttpEntity() {
		if (!bodyMap.isEmpty()) {
			return new HttpEntity<>(bodyMap, headers);
		} else if (bodyEntity != null) {
			return bodyEntity;
		} else {
			return new HttpEntity<>(null, headers);
		}
	}
}
