package com.ahlquist.config;

import java.util.Date;
import java.util.Map;

public class AwsS3Config {

	// These fields are passed into the consuming function
	// metadata.setContentLength(Integer);
	// metadata.setContentType(String);

	// See https://docs.aws.amazon.com/AmazonS3/latest/API/API_PutObject.html
	// for details on these values
	Boolean bucketKeyEnabled;
	String cacheControl;
	String contentDisposition;
	String contentEncoding;
	String contentLanguage;
	String contentMD5;
	Date expirationTime;
	String expirationTimeRuleId;
	// metadata.setHeader(String key, String value);
	Date httpExpiresDate;
	Date lastModified;
	Boolean ongoingRestore;
	Boolean requesterCharged;
	Date restoreExpirationTime;
	String sSEAlgorithm;
	String sSECustomerKeyMd5;
	Map<String, String> userMetadata;
}
