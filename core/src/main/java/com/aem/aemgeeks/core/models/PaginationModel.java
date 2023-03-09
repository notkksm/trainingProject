package com.aem.aemgeeks.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Model(adaptables = Resource.class)
public class PaginationModel {
    @Inject
    private ResourceResolver resourceResolver;
    private List<Resource> items = new ArrayList<Resource>();
    private final String EF_PATH = "/content/experience-fragments/training_project/training-project-experience-fragment/master";
    private final String TEMPLATE_PATH = "/conf/aemProject/editable-templates/settings/wcm/templates";

    @PostConstruct
    protected void init() {
        Resource resource = resourceResolver.getResource(TEMPLATE_PATH);
        Iterator<Resource> childResource = resource.listChildren();
        while (childResource.hasNext()) {
            items.add(childResource.next());
        }
    }

    public List<Resource> getItems() {
        return items;
    }

    public String getPath() {
        return EF_PATH;
    }
}


