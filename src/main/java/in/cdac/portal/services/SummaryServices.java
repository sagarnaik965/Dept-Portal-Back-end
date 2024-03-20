package in.cdac.portal.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface SummaryServices {
	// summ
	public ResponseEntity<byte[]> getSummaryForJasperIgnite(String[] datedata);

	public ResponseEntity<List<String[]>> getSummaryForcsv(String[] datedata, String username);

	public ResponseEntity<byte[]> getSummaryForJasperIgniteapp(String[] datedata);

	public ResponseEntity<List<String[]>> getSummaryForJasperIgnitecsv(String[] datedata);

	public String getDeptcodeFromUsernameforreportIgnite(String username);

	
	// summ
	//public ResponseEntity<byte[]> getSummaryForJasperIgniteapp(String[] datedata, String username);
}
