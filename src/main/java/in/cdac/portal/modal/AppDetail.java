package in.cdac.portal.modal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.fasterxml.jackson.annotation.JsonProperty;

//import com.fasterxml.jackson.annotation.JsonFormat;

//@JsonFormat(shape = JsonFormat.Shape.ARRAY)
@EntityScan
public class AppDetail {

	@Email(message = "Please enter a valid email Id", regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}")
	@NotBlank(message = "Please enter a valid email Id")
	@JsonProperty("email")
	public String email;

	 @NotBlank(message = "Please enter description")
	@JsonProperty("desc")
	public String desc;

	 @NotBlank(message = "Please enter appname")
	@JsonProperty("appname")
	public String appname;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public AppDetail(String email, String desc, String appname) {
		super();
		this.email = email;
		this.desc = desc;
		this.appname = appname;
	}

	public AppDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "email=" + email + ", desc=" + desc + ", appname=" + appname;
	}

}
