package com.sbolo.syk.view.service;

import org.springframework.stereotype.Service;

import oth.common.anotation.CacheAvl;

@Service
public class TestService {
	@CacheAvl
	public int test() {
		return 22/2+3;
	}
}
