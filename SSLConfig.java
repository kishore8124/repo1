
package com.anniyamtech.main;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import lombok.extern.log4j.Log4j2;

@Configuration

@Log4j2
public class SSLConfig {

	@Autowired
	private Environment env;

	@PostConstruct
	private void configureSSL() { // set to TLSv1.1 or TLSv1.2
		System.setProperty("https.protocols", "TLSv1.2");

		System.setProperty("javax.net.ssl.trustStore", env.getProperty("fundtransfer.cer.trust.store"));
		System.setProperty("javax.net.ssl.keyStore", env.getProperty("fundtransfer.cer.key"));
		System.setProperty("javax.net.ssl.trustStoreType", "JKS");

		// System.setProperty("javax.net.ssl.keyStoreType", "JKS");

		System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");
		log.info("Truststore" + System.getProperty("javax.net.ssl.trustStore"));
		log.info("keystore" + System.getProperty("javax.net.ssl.keyStore"));
		log.info("keystoreType" + System.getProperty("javax.net.ssl.keyStoreType"));

		System.setProperty("javax.net.ssl.trustStorePassword", env.getProperty("fundtransfer.trust.password"));
		System.setProperty("javax.net.ssl.keyStorePassword", env.getProperty("fundtransfer.key.password"));

		log.info("KeystorePassword" + System.getProperty("javax.net.ssl.keyStorePassword"));
		log.info("TruststorePassword" + System.getProperty("javax.net.ssl.trustStorePassword"));

	}
}
