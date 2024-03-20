package in.cdac.portal.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface BillingServices {
		public ResponseEntity<byte[]> getBillingDataForPdf(String[] datedata);

		public ResponseEntity<List<String[]>> getBillingDataForDeptCsv(String[] datedata);

		public ResponseEntity<byte[]> getBillingDataForPdfapp(String[] datedata);

		public ResponseEntity<List<String[]>> getBillingDataForAppCsv(String[] datedata);
}
