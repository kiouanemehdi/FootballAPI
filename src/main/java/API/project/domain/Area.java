package API.project.domain;

public class Area {
	
	
    private int id;
    private String name;
    private String countryCode;
    
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Area [id=" + id + ", name=" + name + ", countryCode=" + countryCode + "]";
	}
	


}
