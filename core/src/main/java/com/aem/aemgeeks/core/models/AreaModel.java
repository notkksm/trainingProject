package com.aem.aemgeeks.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.*;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Exporters({
        @Exporter(name = "jackson",extensions = "json",selector = "format-json",
                options = {
                        @ExporterOption(name = "SerializationFeature.WRAP_ROOT_VALUE", value="true")
                }),
        @Exporter(name = "format-xml",extensions = "xml",selector = "format-xml")
})
@Model(
        adaptables = SlingHttpServletRequest.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        resourceType = { "aemProject/components/content/areaListComponent" }
)
@JsonRootName("json-exporter")
@XmlRootElement(name = "xml-exporter")
public class AreaModel {
    @Inject
    Resource componentResource;

    public static List<Map<String, String>> figuresDetailsMap;

    @PostConstruct
    void init() {
        figuresDetailsMap = new ArrayList<>();

        try {
            Resource figuresDetail = componentResource.getChild("figures");
            if (figuresDetail != null) {
                for (Resource figure : figuresDetail.getChildren()) {
                    Map<String, String> figureMap = new HashMap<>();
                    String type = figure.getValueMap().get("type", String.class);
                    figureMap.put("type", type);
                    if (type.equals("circle")) {
                        figureMap.put("radius", figure.getValueMap().get("radius", String.class));
                        Double radius = Double.valueOf(figureMap.get("radius"));
                        figureMap.put("area", String.valueOf(3.14 * radius * radius));
                    } else if (type.equals("rectangle")) {
                        figureMap.put("side", figure.getValueMap().get("firstside", String.class));
                        figureMap.put("secondside", figure.getValueMap().get("secondside", String.class));
                        Double area = Double.valueOf(figureMap.get("side")) * Double.valueOf(figureMap.get("secondside"));
                        figureMap.put("area", String.valueOf(area));
                    } else if (type.equals("square")) {
                        figureMap.put("side", figure.getValueMap().get("side", String.class));
                        Double side = Double.valueOf(figureMap.get("side"));
                        figureMap.put("area", String.valueOf(side * side));
                    }
                    figuresDetailsMap.add(figureMap);
                }
            }
        } catch (Exception e) {
        }
    }
    @JsonIgnore
    public List<Map<String, String>> getBookDetailsWithMap() {
        return figuresDetailsMap;
    }

    @JsonProperty(value = "json-figures")
    @XmlElement(name = "figure")
    public Map<String, String> getAllFigures() {
        Map<String, String> map = new HashMap<>();
        for (Map<String, String> list : figuresDetailsMap) {
            map.put("type:area", list.get("type") + list.get("area"));
        }
        return map;
    }
}