package ro.digix.beans;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ro.digix.constants.ApplicationConstants;
import ro.digix.services.UserFileService;

@Component("uploadMB")
@Scope("request")
public class UploadMB extends BaseMB {

	@Autowired
	private UserMB userMB;

	@Autowired
	private UserFileService userFileService;

	private Part file1;
	private Part file2;

	private String newFileType;
	private List<StringBuilder> tagsList;

	@PostConstruct
	public void init() {
		tagsList = new ArrayList<>();
	}

	public String getNewFileType() {
		return newFileType;
	}

	public void setNewFileType(String newFileType) {
		this.newFileType = newFileType;
	}

	public void setUserMB(UserMB userMB) {
		this.userMB = userMB;
	}

	public void setUserFileService(UserFileService userFileService) {
		this.userFileService = userFileService;
	}

	public Part getFile1() {
		return file1;
	}

	public void setFile1(Part file1) {
		this.file1 = file1;
	}

	public Part getFile2() {
		return file2;
	}

	public String upload() throws IOException {

		file1.write(ApplicationConstants.CALE_UPLOAD + File.separator + userMB.getEmail() + File.separator
				+ getFilename(file1));
		return getRedirectedURL("yourFiles.xhtml");
	}

	private static String getFilename(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
				return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE
																													// fix.
			}
		}
		return null;
	}
}
