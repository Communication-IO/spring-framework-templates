package com.ahlquist.commio;

import org.springframework.boot.env.YamlPropertySourceLoader;

public class JsonPropertySourceLoader 
               extends YamlPropertySourceLoader {
				   
	@Override
	public String[] getFileExtensions() {
		return new String[] { "json" };
	}
}