package com.book.config.jasypt;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableEncryptableProperties
public class JasyptConfigAES {

	@Value("${ENCRYPTOR.PASSWORD}")
	private String password;
	@Value("${ENCRYPTOR.ALGORITHM}")
	private String algorithm;

	@Bean("jasyptEncryptorAES")
	public StringEncryptor stringEncryptor() {
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();

		config.setPassword(password); // 암호화 키
		config.setAlgorithm(algorithm); // 알고리즘
		config.setKeyObtentionIterations("1000"); // 반복할 해싱 회수
		config.setPoolSize("1"); // 인스턴스 pool
		config.setProviderName("SunJCE");
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator"); // salt 생성 클래스
		config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
		config.setStringOutputType("base64"); // 인코딩 방식
		encryptor.setConfig(config);
		return encryptor;
	}
}