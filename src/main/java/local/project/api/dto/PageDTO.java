package local.project.api.dto;

import java.io.Serializable;

import org.springframework.data.domain.PageRequest;

public class PageDTO implements Serializable {

    
	private static final long serialVersionUID = 5946468583005150707L;

	private int page;
	private int size;
	
	//need default constructor for JSON Parsing
	public PageDTO()
	{
		
    }
    
    public PageDTO(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public PageRequest toPageRequest() {
        return PageRequest.of(this.page, this.size);
    }

    /**
     * @return the page
     */
    public int getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }
    

}