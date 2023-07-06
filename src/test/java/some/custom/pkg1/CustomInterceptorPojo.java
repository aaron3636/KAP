package some.custom.pkg1;

import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.StringType;
import ca.uhn.fhir.interceptor.api.Hook;
import ca.uhn.fhir.interceptor.api.Pointcut;
import ca.uhn.fhir.rest.api.RestOperationTypeEnum;
import ca.uhn.fhir.rest.server.servlet.ServletRequestDetails;

public class CustomInterceptorPojo {

	@Hook(Pointcut.SERVER_INCOMING_REQUEST_PRE_HANDLED)
	void preHandleResource(ServletRequestDetails servletRequestDetails, RestOperationTypeEnum opType) {
		IBaseResource resource = servletRequestDetails.getResource();

		// add an extension before saving the resource to mark it
		if (opType == RestOperationTypeEnum.CREATE && resource instanceof Patient) {
			Patient pat = (Patient) resource;
			Extension ext = pat.addExtension();
			ext.setUrl("http://some.custom.pkg1/CustomInterceptorPojo");
			ext.setValue(new StringType("CustomInterceptorPojo wuz here"));
		}
	}
}
