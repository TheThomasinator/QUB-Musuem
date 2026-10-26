package part01;

public class ArtifactExhibitSign {
	private String artifactId;
	private String exhibitId;
	private String sign;
	
	public ArtifactExhibitSign(String artifactId, String exhibitId, String sign) {
		this.artifactId = artifactId;
		this.exhibitId = exhibitId;
		this.sign = sign;
	}
	
	public String getSign() {
		return sign;
	}
	
	public void setSign(String input) {
		sign = input;
	}
	
	public String getArtifactId() {
		return artifactId;
	}
	
	public String getExhibitId() {
		return exhibitId;
	}
	
	
}
