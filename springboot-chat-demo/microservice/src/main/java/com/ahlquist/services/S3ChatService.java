package com.ahlquist.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ahlquist.app.Application;
import com.ahlquist.aws.S3BucketSingleton;
import com.amazonaws.services.s3.model.ObjectMetadata;

/**
 * Utility Service Used to Upload files to AWS
 * NOTE: this should extend an interface that could be cause to have in testing or other cases to 
 * 	have the file written to a local file system
 * 
 * @author douglasahlquist
 */
public class S3ChatService {

	private static final Logger logger = LoggerFactory.getLogger(S3ChatService.class);
	public static final int SIZE = 5_242_880;

	public int lastSegmentSize = 0;

	/**
	 * Uploads a file (maybe in parts to an S3 bucket.
	 * (TODO) we should later cause another service to combine these pieces, so that the multiple part can be retrieved as one.
	 * @param stream - the stream from the controller or other caller
	 * @param sender - the sender's UUID
	 * @param recipient - the recipient's UUID
	 * @param contentType - the MimeType
	 * @return The list of keys representing the multiple parts uploaded
	 * @throws IOException
	 */
	public List<String> uploadFile(@NonNull InputStream stream, final @NonNull String filename,
			final @NonNull UUID sender, final @NonNull UUID recipient, final @NonNull String contentType)
			throws IOException {

		logger.debug("InputStream filename: " + filename);

		// Process the input stream
		int part = 1;
		byte[] data = new byte[SIZE];
		List<String> porList = new ArrayList<>();
		
		@SuppressWarnings("unused")
		int nRead = 0;
		while ((nRead = stream.read(data, 0, data.length)) != -1) {

			// create input stream from byte array
			InputStream inputStreamPart = new ByteArrayInputStream(data);

			String key = sender.toString() + "-" + System.currentTimeMillis() + "_" + 
					recipient.toString() + "." + part++;

			logger.debug("new key: " + key);

			// Other values could be added
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(data.length);
			this.lastSegmentSize = data.length;
			metadata.setContentType(contentType);

			// S3BucketSingleton.getInstance().getName() - the name (required)
			// key - the name of the file and other identify data (required)
			// inputStreamPart the inputStream portion of the file (required)
			// metadata - AWS specific settings for the object
			/* DKA PutObjectResult por = */ Application.s3Service.putObjectFromInputStream(
					S3BucketSingleton.getInstance().getName(), key, inputStreamPart, metadata);
			porList.add(key);
		}
		return porList;
	}

	public int getLastSegmentSize() {
		return lastSegmentSize;
	}
}
