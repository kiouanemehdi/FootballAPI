package API.project.domain;

public class Season {
	
	private int id;
    private String startDate;
    private String endDate;
    private int currentMatchday;
    private Object winner;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getCurrentMatchday() {
		return currentMatchday;
	}
	public void setCurrentMatchday(int currentMatchday) {
		this.currentMatchday = currentMatchday;
	}
	public Object getWinner() {
		return winner;
	}
	public void setWinner(Object winner) {
		this.winner = winner;
	}
	@Override
	public String toString() {
		return "Season [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", currentMatchday="
				+ currentMatchday + ", winner=" + winner + "]";
	}
	

}
