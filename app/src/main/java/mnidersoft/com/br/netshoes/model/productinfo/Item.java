package mnidersoft.com.br.netshoes.model.productinfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    @JsonProperty("zoom")
    private String zoom;
    @JsonProperty("large")
    private String large;

    public String getZoom() {
        return this.zoom;
    }

    public void setZoom(String zoom) {
        this.zoom = zoom;
    }

    public String getLarge() {
        return this.large;
    }

    public void setLarge(String large) {
        this.large = large;
    }
}