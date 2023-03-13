package com.aem.aemgeeks.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = SlingHttpServletRequest.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ImageLinkModel {
    @ValueMapValue
    private String logo;
    @ValueMapValue
    private String logoLink;
    @ValueMapValue
    private String sponsor;
    @ValueMapValue
    private String sponsorLink;

    public String getLogo() {
        return logo;
    }

    public String getLogoLink() {
        return logoLink;
    }

    public String getSponsor() {
        return sponsor;
    }

    public String getSponsorLink() {
        return sponsorLink;
    }
}




