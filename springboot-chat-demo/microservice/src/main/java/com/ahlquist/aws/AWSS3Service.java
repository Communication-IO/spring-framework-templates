package com.ahlquist.aws;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CopyObjectResult;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.DeleteObjectsResult;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;

public class AWSS3Service {

	private static final Logger logger = LoggerFactory.getLogger(AWSS3Service.class);

	private static AmazonS3 s3client = null;
	@SuppressWarnings("unused")
	private static final AWSCredentials credentials;

	static {
		// Read the Credentials from your system's ~/.aws file
		String userDir = System.getProperty("user.home");
		String accessKey = null;
		String secretKey = null;

		String path = userDir + File.separator + ".aws" + File.separator + "credentials";
		logger.debug("opening: " + path);

		try (InputStream is = new FileInputStream(path)) {

			Properties prop = new Properties();
			prop.load(is);
			accessKey = (String) prop.get("aws_access_key_id");
			secretKey = (String) prop.get("aws_secret_access_key");

		} catch (IOException io) {
			logger.error("Exception reading: " + io);
		}

		credentials = new BasicAWSCredentials(accessKey, secretKey);
		s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(Regions.US_EAST_2).build();

	}

	public AWSS3Service() {
		super();
	}

	// is bucket exist?
	@SuppressWarnings("deprecation")
	public boolean doesBucketExist(String bucketName) {
		return s3client.doesBucketExist(bucketName);
	}

	// create a bucket
	public Bucket createBucket(String bucketName) {
		if (!s3client.doesBucketExistV2(bucketName)) {
			return s3client.createBucket(bucketName);
		}
		List<Bucket> list = s3client.listBuckets();
		for (Bucket b : list) {
			if (b.getName().equals(bucketName)) {
				return b;
			}
		}
		return null;
	}

	// list all buckets
	public List<Bucket> listBuckets() {
		return s3client.listBuckets();
	}

	// delete a bucket
	public void deleteBucket(String bucketName) {
		s3client.deleteBucket(bucketName);
	}

	// uploading object

	public PutObjectResult putObject(String bucketName, String key, File file) {
		return s3client.putObject(bucketName, key, file);
	}

	public PutObjectResult putObjectFromInputStream(String bucketName, String key, InputStream input,
			ObjectMetadata metadata) {
		return s3client.putObject(bucketName, key, input, metadata);
	}

	// listing objects
	public ObjectListing listObjects(String bucketName) {
		return s3client.listObjects(bucketName);
	}

	// get an object
	public S3Object getObject(String bucketName, String objectKey) {
		return s3client.getObject(bucketName, objectKey);
	}

	// copying an object
	public CopyObjectResult copyObject(String sourceBucketName, String sourceKey, String destinationBucketName,
			String destinationKey) {
		return s3client.copyObject(sourceBucketName, sourceKey, destinationBucketName, destinationKey);
	}

	// deleting an object
	public void deleteObject(String bucketName, String objectKey) {
		s3client.deleteObject(bucketName, objectKey);
	}

	// deleting multiple Objects
	public DeleteObjectsResult deleteObjects(DeleteObjectsRequest delObjReq) {
		return s3client.deleteObjects(delObjReq);
	}
}