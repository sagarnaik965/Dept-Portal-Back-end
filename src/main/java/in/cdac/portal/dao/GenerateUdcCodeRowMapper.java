package in.cdac.portal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import in.cdac.portal.modal.GenerateUdcCode;

public class GenerateUdcCodeRowMapper implements RowMapper<GenerateUdcCode>{

	public GenerateUdcCode mapRow(ResultSet rs, int rowNum) throws SQLException {
		GenerateUdcCode generateUdcCode = new GenerateUdcCode();
		generateUdcCode.setUdc(rs.getString("udc"));
		generateUdcCode.setVendorCode(rs.getString("vendor_code"));
		return generateUdcCode;
		
	}

}
