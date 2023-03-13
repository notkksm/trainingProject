package com.aem.aemgeeks.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Model(adaptables = SlingHttpServletRequest.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class NavigationItemModel {
    @Inject
    Resource componentResource;
    @ValueMapValue
    private String title;
    @ValueMapValue
    private String link;

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public List<Map<String, String>> getLinkDetailsWithMap() {
        List<Map<String, String>> linksDetailsMap = new ArrayList<>();
        try {
            Resource linkDetail = componentResource.getChild("links");
            if (linkDetail != null) {
                for (Resource link : linkDetail.getChildren()) {
                    Map<String, String> linkMap = new HashMap<>();
                    linkMap.put("title", link.getValueMap().get("title", String.class));
                    linkMap.put("link", link.getValueMap().get("link", String.class));
                    linksDetailsMap.add(linkMap);
                }
            }
        } catch (Exception e) {

        }
        return linksDetailsMap;
    }
}
