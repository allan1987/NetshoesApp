
package mnidersoft.com.br.netshoes.model.productinfo;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "selected",
    "name",
    "value"
})
public class HiddenField {

    @JsonProperty("selected")
    private Object selected;
    @JsonProperty("name")
    private String name;
    @JsonProperty("value")
    private String value;

    /**
     * 
     * @return
     *     The selected
     */
    @JsonProperty("selected")
    public Object getSelected() {
        return selected;
    }

    /**
     * 
     * @param selected
     *     The selected
     */
    @JsonProperty("selected")
    public void setSelected(Object selected) {
        this.selected = selected;
    }

    /**
     * 
     * @return
     *     The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The value
     */
    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    /**
     * 
     * @param value
     *     The value
     */
    @JsonProperty("value")
    public void setValue(String value) {
        this.value = value;
    }

}
