package douglas.bookself.utils;

import static java.lang.System.currentTimeMillis;

import java.io.IOException;

import javax.servlet.http.Part;

import douglas.bookself.servlet.CoversServlet;


public class UploadUtils {
	public static String saveCover(Part fileUpload) {
		if (!fileUpload.getContentType().startsWith("image/"))
			return null;

		try {
			String filename = UploadUtils.getFilename(fileUpload);
			String fileId = currentTimeMillis() + "-" + filename;

			fileUpload.write(CoversServlet.COVERS_PATH + fileId);
			return fileId;
		} catch (IOException e) {
			return null;
		}
	}

	public static String getFilename(Part file) {
		if (file == null) return "";

		for (String cd : file.getHeader("content-disposition").split(";")) {
			cd = cd.trim();
			if (cd.startsWith("filename=")) {
				return cd.substring(10, cd.length() - 1);
			}
		}
		return "";
	}
}
