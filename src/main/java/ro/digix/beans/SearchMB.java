package ro.digix.beans;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ro.digix.entities.FileTag;
import ro.digix.entities.User;
import ro.digix.entities.UserFile;
import ro.digix.services.UserFileService;
import ro.digix.services.UserService;

@Component("searchMB")
@Scope("request")
public class SearchMB extends BaseMB {

	@Autowired
	private UserMB userMB;

	@Autowired
	private UserService userService;

	@Autowired
	private UserFileService userFileService;

	private String searchQuery;

	private Map<Long, Integer> filesToReturn;

	@PostConstruct
	public void init() {
		filesToReturn = new HashMap<>();
	}

	public String getSearchQuery() {
		return searchQuery;
	}

	public void setUserFileService(UserFileService userFileService) {
		this.userFileService = userFileService;
	}

	public void setUserMB(UserMB userMB) {
		this.userMB = userMB;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}

	public String search() throws IOException {
		userMB.getSearchResult().clear();
		if (searchQuery == null) {
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		}

		boolean picture = false;
		boolean video = false;
		boolean file = false;

		if (askedForPictures()) {
			picture = true;
		}

		if (askedForVideos()) {
			video = true;
		}

		if (askedForFiles()) {
			file = true;
		}
		
		if (video) {
			System.out.println("video");
		}
		if(picture) {
			System.out.println("picture");
		}
		if(file){
			System.out.println("file");
		}
		if (!picture && !video && !file) {
			System.out.println("Am intrat in toate");
			searchThroughAllFiles();
		} else if (!video && !file && picture) {
			System.out.println("Am intrat in poze");
			searchThroughPictures();
		} else if (!picture && !file && video) {
			System.out.println("Am intrat in filme");
			searchThroughVideos();
		} else if (!picture && !video && file) {
			System.out.println("Am intrat in fisiere");
			searchThroughFiles();
		} else if (!file) {
			System.out.println("Am intrat in poze si filme");
			searchThroughPicturesAndVideos();
		}
		userMB.setSearchedQuery(searchQuery);
		return getRedirectedURL("searchResult.xhtml");
	}

	private void searchThroughFiles() {
		List<UserFile> files = userService.getAllMineAndFriendsFiles(userMB.getId(), false, false, true);
		populateList(files);
	}

	private void searchThroughVideos() {
		List<UserFile> files = userService.getAllMineAndFriendsFiles(userMB.getId(), false, true, false);
		populateList(files);
	}

	private void searchThroughPictures() {
		List<UserFile> files = userService.getAllMineAndFriendsFiles(userMB.getId(), true, false, false);
		populateList(files);
	}

	private void searchThroughPicturesAndVideos() {
		List<UserFile> files = userService.getAllMineAndFriendsFiles(userMB.getId(), true, true, false);
		populateList(files);
	}

	private void searchThroughAllFiles() {
		List<UserFile> files = userService.getAllMineAndFriendsFiles(userMB.getId());
		populateList(files);

	}

	private void populateList(List<UserFile> files) {
		String[] words = searchQuery.split("\\W+");
		System.out.println();
		for (UserFile uf : files) {
			int nrOfOccurences = 0;
			for (FileTag ft : uf.getFileTags()) {
				for (String s : words) {
					if (s.equalsIgnoreCase(ft.getTag())) {
						nrOfOccurences++;
					}
				}
			}
			if (nrOfOccurences > 0) {
				filesToReturn.put(uf.getId(), nrOfOccurences);
			}
		}

		filesToReturn = sortByComparator(filesToReturn);

		for (Long l : filesToReturn.keySet()) {
			userMB.getSearchResult().add(userFileService.getUserFileById(l));
		}
	}

	private static Map<Long, Integer> sortByComparator(Map<Long, Integer> unsortMap) {

		// Convert Map to List
		List<Map.Entry<Long, Integer>> list = new LinkedList<Map.Entry<Long, Integer>>(unsortMap.entrySet());

		// Sort list with comparator, to compare the Map values
		Collections.sort(list, new Comparator<Map.Entry<Long, Integer>>() {
			public int compare(Map.Entry<Long, Integer> o1, Map.Entry<Long, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		// Convert sorted map back to a Map
		Map<Long, Integer> sortedMap = new LinkedHashMap<Long, Integer>();
		for (Iterator<Map.Entry<Long, Integer>> it = list.iterator(); it.hasNext();) {
			Map.Entry<Long, Integer> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

	public static void printMap(Map<String, Integer> map) {
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println("[Key] : " + entry.getKey() + " [Value] : " + entry.getValue());
		}
	}

	private boolean askedForFiles() {
		String[] words = searchQuery.split("\\W+");
		if (words.length > 1) {
			if (searchForFileWords(words[0])) {
				return true;
			}
		}
		if (words.length > 3) {
			if (searchForFileWords(words[2])) {
				return true;
			}
		}
		if (words.length > 6) {
			if (searchForFileWords(words[4])) {
				return true;
			}
		}

		return false;
	}

	private boolean searchForFileWords(String s) {
		if (s.equalsIgnoreCase("acte") || s.equalsIgnoreCase("act") || s.equalsIgnoreCase("scrisori")
				|| s.equalsIgnoreCase("scrisoare")) {
			return true;
		}

		return false;
	}

	private boolean askedForVideos() {
		String[] words = searchQuery.split("\\W+");
		System.out.println("Asked for videos " + words.length);
		if (words.length > 1) {
			if (searchForVideoWords(words[0])) {
				return true;
			}
		}
		if (words.length > 3) {
			System.out.println("urmeaza sa caut in " + words[2]);
			if (searchForVideoWords(words[2])) {
				return true;
			}
		}
		if (words.length > 6) {
			if (searchForVideoWords(words[4])) {
				return true;
			}
		}

		return false;
	}

	private boolean searchForVideoWords(String s) {
		if (s.equalsIgnoreCase("video") || s.equalsIgnoreCase("clipuri") || s.equalsIgnoreCase("videoclipuri")
				|| s.equalsIgnoreCase("filme") || s.equalsIgnoreCase("video-uri") || s.equalsIgnoreCase("filmulete")) {
			return true;
		}

		return false;
	}

	private boolean askedForPictures() {
		String[] words = searchQuery.split("\\W+");
		if (words.length > 1) {
			if (searchForPictureWords(words[0])) {
				return true;
			}
		}
		if (words.length > 3) {
			if (searchForPictureWords(words[2])) {
				return true;
			}
		}
		if (words.length > 6) {
			if (searchForPictureWords(words[4])) {
				return true;
			}
		}

		return false;
	}

	private boolean searchForPictureWords(String s) {
		if (s.equalsIgnoreCase("poze") || s.equalsIgnoreCase("poza") || s.equalsIgnoreCase("imagini")
				|| s.equalsIgnoreCase("imagine") || s.equalsIgnoreCase("fotografii")
				|| s.equalsIgnoreCase("fotografie")) {
			return true;
		}

		return false;
	}

}
