package ro.digix.beans;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("searchMB")
@Scope("request")
public class SearchMB extends BaseMB {

	@Autowired
	private UserMB userMB;
	
	private String searchQuery;

	public String getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}
	
	public void search() throws IOException{
		if(searchQuery == null) {
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		    ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		}
		
		
	}

}
