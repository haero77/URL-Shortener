
package com.haero77.urlshortener.domain.url.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.haero77.urlshortener.domain.url.dto.ShortUrlCreateRequest;
import com.haero77.urlshortener.domain.url.entity.ShortUrlStatus;
import com.haero77.urlshortener.domain.url.entity.Url;
import com.haero77.urlshortener.domain.url.repository.UrlRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class UrlCreatorTest {

	@Autowired
	private UrlRepository urlRepository;

	@Autowired
	private UrlCreator urlCreator;

	@AfterEach
	void tearDown() {
		urlRepository.deleteAll();
	}

	@Test
	@DisplayName("ShortUrl을 생성하고 나면 상태는 ACTIVE이다.")
	void create() {
		// given
		ShortUrlCreateRequest createRequest =
			new ShortUrlCreateRequest("https://github.com/haero77", false);

		Long savedId = urlCreator.create(createRequest);

		// when
		Url findUrl = urlRepository.findById(savedId).get();

		// then
		assertThat(findUrl.status()).isSameAs(ShortUrlStatus.ACTIVE);
	}
}