/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.xing.connect;

import org.springframework.social.oauth1.OAuth1Version;
import org.springframework.social.xing.api.Xing;
import org.springframework.social.xing.api.impl.XingTemplate;
import org.springframework.social.oauth1.AbstractOAuth1ServiceProvider;
import org.springframework.social.oauth1.OAuth1Template;
import org.springframework.util.StringUtils;

/**
 * Xing ServiceProvider implementation.
 * @author Johannes Buehler
 */
public class XingServiceProvider extends AbstractOAuth1ServiceProvider<Xing> {
	
	private final String xingBaseUrl;
	
	static final String DEFAULT_XING_BASE = "https://api.xing.com/v1";
	
	public XingServiceProvider(String xingBaseUrl, String consumerKey, String consumerSecret) {
		super(consumerKey, consumerSecret, new OAuth1Template(consumerKey, consumerSecret,
                getBaseUrl(xingBaseUrl) + "/request_token",
                getBaseUrl(xingBaseUrl) + "/authorize",
                getBaseUrl(xingBaseUrl) + "/access_token",
				OAuth1Version.CORE_10_REVISION_A));
		this.xingBaseUrl = getBaseUrl(xingBaseUrl);
	}

    private static String getBaseUrl(String xingBaseUrl) {
        return StringUtils.isEmpty(xingBaseUrl) ? DEFAULT_XING_BASE : xingBaseUrl;
    }

    public XingServiceProvider(String consumerKey, String consumerSecret) {
		this(null, consumerKey, consumerSecret);
	}
	
	public Xing getApi(String accessToken, String secret) {
		return new XingTemplate(xingBaseUrl, getConsumerKey(), getConsumerSecret(), accessToken, secret);
	}
}