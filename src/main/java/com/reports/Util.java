package com.reports;

import java.io.File;
import java.io.IOException;

import org.testcontainers.shaded.org.apache.commons.io.FileUtils;

import com.github.dockerjava.zerodep.shaded.org.apache.commons.codec.binary.Base64;

public class Util {
	public static String covertImg_base64(String screenshotPath) throws IOException{
		byte[] file = FileUtils.readFileToByteArray(new File(screenshotPath));
		String base64Img = Base64.encodeBase64String(file);
		return base64Img;
	}

}
