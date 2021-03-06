package eu.cloudopting.bpmn.tasks.deploy;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.cloudopting.domain.Customizations;
import eu.cloudopting.domain.Status;
import eu.cloudopting.service.CustomizationService;
import eu.cloudopting.tosca.ToscaService;

@Service
public class DeployManageCustomization implements JavaDelegate {
	private final Logger log = LoggerFactory.getLogger(DeployManageCustomization.class);
	@Autowired
	ToscaService toscaService;
	
	@Autowired
	CustomizationService customizationS;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		log.info("in DeployManageCustomization");
		String customizationId = (String) execution.getVariable("customizationId");
		Customizations theCust = customizationS.findOne(Long.parseLong(customizationId));
		String cloudProvider = theCust.getCloudAccount().getProviderId().getProvider();
//		Status myStatus = theCust.getStatusId();
//		myStatus.setId((long) 90);
//		log.info(theCust.toString());
//		theCust.setProcessId(execution.getProcessInstanceId());
 //       theCust.setStatusId(myStatus);
		// @TODO will have to remove the comment in production, but now for developing setting the ID is a pain 
//		customizationS.update(theCust);
		toscaService.setToscaCustomization(customizationId, theCust.getCustomizationToscaFile());
//		execution.setVariable("organizationId", theCust.getCustomerOrganizationId().getOrganizationKey());
		execution.setVariable("provider", "csi");
//		execution.setVariable("cloudId", cloudProvider);
		
		
	}

}
