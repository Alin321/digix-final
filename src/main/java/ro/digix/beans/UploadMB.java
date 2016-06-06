package ro.digix.beans;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ro.digix.constants.ApplicationConstants;
import ro.digix.entities.FileTag;
import ro.digix.entities.User;
import ro.digix.entities.UserFile;
import ro.digix.services.FileTagService;
import ro.digix.services.UserFileService;

@Component("uploadMB")
@Scope("request")
public class UploadMB extends BaseMB {

	@Autowired
	private UserMB userMB;

	@Autowired
	private UserFileService userFileService;

	@Autowired
	private FileTagService fileTagService;

	private Part file1;
	private Part file2;

	private boolean haveCategoryInList;
	private String newFileType;
	private String tags;
	private String accessType;
	private String fileType;

	private List<FileTag> tagsList;

	public boolean isHaveCategoryInList() {
		return haveCategoryInList;
	}

	public void setHaveCategoryInList(boolean haveCategoryInList) {
		this.haveCategoryInList = haveCategoryInList;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public void setFileTagService(FileTagService fileTagService) {
		this.fileTagService = fileTagService;
	}

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

	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
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

		String uploads = ApplicationConstants.CALE_WORKSPACE + File.separator + userMB.getEmail();
		try (InputStream input = file1.getInputStream()) {
			Files.copy(input, new File(uploads, getFilename(file1)).toPath());
		}
		file1.write(ApplicationConstants.CALE_UPLOAD + File.separator + userMB.getEmail() + File.separator
				+ getFilename(file1));
		User user = new User();
		user.setId(userMB.getId());

		UserFile uf = new UserFile();

		if (haveCategoryInList) {
			uf.setType(newFileType);
		} else {
			uf.setType(fileType);
		}

		uf.setAccessType(accessType);

		uf.setDateAdded(Calendar.getInstance().getTime());
		uf.setId(userFileService.getNewId());
		uf.setUser(user);
		uf.setLocation("upload" + "/" + userMB.getEmail() + "/" + getFilename(file1));
		uf = userFileService.create(uf);
		generateFileTags(uf);

		return getRedirectedURL("yourFiles.xhtml");
	}

	private void generateFileTags(UserFile uf) {
		String[] words = tags.split("\\W+");
		for (String word : words) {
			FileTag ft = new FileTag();
			ft.setId(fileTagService.getNextId());
			ft.setUserFile(uf);
			ft.setTag(word);
			tagsList.add(ft);
			fileTagService.create(ft);
		}

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
