package com.haero77.urlshortener.domain.url.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.haero77.urlshortener.domain.url.entity.Referer;
import com.haero77.urlshortener.domain.url.entity.Url;
import com.haero77.urlshortener.domain.url.entity.UrlCall;
import com.haero77.urlshortener.domain.url.repository.UrlCallRepository;
import com.haero77.urlshortener.domain.url.util.TimeUtil;

@Service
@Transactional
public class UrlCallCreator {

	private final UrlCallRepository urlCallRepository;

	public UrlCallCreator(UrlCallRepository urlCallRepository) {
		this.urlCallRepository = urlCallRepository;
	}

	public Long create(Url url, Referer referer) {
		UrlCall urlCall = new UrlCall(url, referer, TimeUtil.getCurrentSeoulTime());
		urlCallRepository.save(urlCall);
		return urlCall.id();
	}
}
