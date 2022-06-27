package douglas.bookself.utils;

import static java.lang.System.currentTimeMillis;

import java.io.IOException;

import javax.servlet.http.Part;

public class UploadUtils {
	private static final String coverPath = "D:/Douglas/Faculdade/AYTY/Onboarding ESIG/EclipseWorkspace2/bookself/src/main/webapp/resources/images/";

	public static String saveCover(Part fileUpload) {
		if (!fileUpload.getContentType().startsWith("image/"))
			return null;

		try {
			String filename = UploadUtils.getFilename(fileUpload);
			String fileId = currentTimeMillis() + "-" + filename;
			fileUpload.write(UploadUtils.coverPath + fileId);

			return fileId;
		} catch (IOException e) {
			return null;
		}
	}

	private static String getFilename(Part file) {
		for (String cd : file.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String filename = cd.substring(cd.indexOf('-') + 1).trim().replace("\"", "");
				return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
			}
		}
		return "";
	}
}
