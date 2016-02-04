package eu.cloudopting.web.rest;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import eu.cloudopting.bpmn.BpmnService;
import eu.cloudopting.domain.Customizations;
import eu.cloudopting.dto.CustomizationDTO;
import eu.cloudopting.events.api.controller.AbstractController;
import eu.cloudopting.events.api.service.BaseService;
import eu.cloudopting.service.CustomizationService;

/**
 * Created by danielpo on 19/03/2015.
 */
@RestController
@RequestMapping("/api")
public class CustomizationResource  extends AbstractController<Customizations> {
   @Inject
   CustomizationService customizationService;
    /**
     * Default contructor.
     *
     */
    public CustomizationResource() {
        super(Customizations.class);
    }

    @Inject
    private BpmnService bpmnService;


    public BpmnService getBpmnService() {
        return bpmnService;
    }


    @Override
    protected BaseService<Customizations> getService() {
        return customizationService;
    }

    @RequestMapping(value = "/customization/{id}", method = RequestMethod.GET)
    @ResponseBody
    public final Customizations findOne(@PathVariable("id") final Long id, final UriComponentsBuilder uriBuilder,
                                      final HttpServletResponse response) {
        return findOneInternal(id);
    }

    @RequestMapping(value = "/customization", method = RequestMethod.GET)
    @ResponseBody
    public final List<Customizations> findAll(final UriComponentsBuilder uriBuilder,
                                        final HttpServletResponse response, final HttpServletRequest request) {
        return findAllInternal(request, uriBuilder, response);
    }

    //Method not used. For customization creation is used /application/{idApp}/sendCustomizationForm in CustomizationController.
//    @RequestMapping(value="/customization",method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.CREATED)
//    @ResponseBody
//    public final void create(@RequestBody CustomizationDTO customizationDTO, final UriComponentsBuilder uriBuilder,
//                             final HttpServletResponse response, final HttpServletRequest request) {
//        getBpmnService().createCustomization(customizationDTO);
//    }

    @RequestMapping(value="/customization/{customizationId}",method = RequestMethod.DELETE,  produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public final void delete(@PathVariable String customizationId, final UriComponentsBuilder uriBuilder,
                             final HttpServletResponse response, final HttpServletRequest request) {
        getBpmnService().deleteCustomization(customizationId);
    }


    @RequestMapping(value="/customization",method = RequestMethod.PUT,  produces = MediaType.APPLICATION_JSON_VALUE)
    public final void update(@RequestBody CustomizationDTO customizationDTO, final UriComponentsBuilder uriBuilder,
                             final HttpServletResponse response, final HttpServletRequest request) {
        Customizations customization = getService().findOne(customizationDTO.getId());
		if (customization == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		} 
		response.setStatus(HttpServletResponse.SC_OK);
		((CustomizationService)getService()).update(customizationDTO);
    }
}
