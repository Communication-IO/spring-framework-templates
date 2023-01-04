package com.ahlquist.aws;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import com.ahlquist.services.S3ChatService;

public class PutObjectTest {

	public static void main(String... strings) {

		S3ChatService s3s = new S3ChatService();

		//TODO (dahlquist) : the path should point to an actual file.  The could also be moved to 
		// a command line argument, but the author doesn't want this run every time, because of costs for AWS.
		// Also the contentType should be changed or be an input argument.....   or the whole thing mocked.
		String filenameFullPath = "/Users/douglasahlquist/Documents/workspace_asapp2/microservice/src/scripts/images/bigImage-20MB.jpg";
		File file = new File(filenameFullPath);
		try {
			InputStream stream = new DataInputStream(new FileInputStream(file));
			String filename = file.getName();
			UUID sender = UUID.randomUUID();
			UUID recipient = UUID.randomUUID();
			String contentType = "image/jpeg";

			List<String> list = s3s.uploadFile(stream, filename, sender, recipient, contentType);
			for (String por : list) {
				System.out.println(por.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
