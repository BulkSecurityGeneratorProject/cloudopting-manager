package eu.cloudopting.bpmn.tasks.publish;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PublishUploadToscaPackage implements JavaDelegate {
	private final Logger log = LoggerFactory.getLogger(PublishUploadToscaPackage.class);
//  TODO Find the Jackrabbit Wrapper Service
//	@Autowired
//	ToscaService toscaService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		log.info("Uploading the Tosca Package");
		
	}

}
