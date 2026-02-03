package com.demo.playground.service;

import io.github.resilience4j.retry.RetryRegistry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RetryServiceTest {

    @Autowired
    private RetryService retryService;

    @MockitoBean
    private WebClient.Builder webClientBuilder;

    @MockitoBean
    private WebClient webClient;

    @MockitoBean
    private WebClient.RequestBodyUriSpec requestBodyUriSpec;

    @MockitoBean
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @MockitoBean
    private WebClient.ResponseSpec responseSpec;

    @Autowired
    private RetryRegistry retryRegistry;

    @Test
    public void testRetryLogic() {
        // Setup mocks
        when(webClientBuilder.build()).thenReturn(webClient);
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.retrieve()).thenReturn(responseSpec);

        // Simulate failure
        when(responseSpec.bodyToMono(eq(String.class))).thenReturn(Mono.error(new RuntimeException("Simulated Retry Error")));

        // Call service
        String response = retryService.callTargetApi(true);

        // Verify fallback
        assertTrue(response.startsWith("Fallback response: Retry limit reached"));

        // Verify that it retried 3 times (initial + 2 retries = 3 attempts total)
        verify(webClientBuilder, times(3)).build();
    }
}
