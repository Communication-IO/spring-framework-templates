package com.ahlquist.aws;

public class S3BucketSingleton {

	private static S3BucketSingleton instance = null;
	AWSS3Service service = null;

	public static String BUCKET_NAME = "chatapp.12345";

	private S3BucketSingleton() {
		super();

		// Let's Try to create an s3 bucket with the name by value BUCKET_NAME
		service = new AWSS3Service();
		service.createBucket(BUCKET_NAME);
	}

	public static S3BucketSingleton getInstance() {
		if (instance == null) {
			instance = new S3BucketSingleton();
		}
		return instance;
	}

	public AWSS3Service getService() {
		return service;
	}

	public static void setInstance(S3BucketSingleton instance) {
		S3BucketSingleton.instance = instance;
	}

	public String getName() {
		return BUCKET_NAME;
	}
}
