
package mnidersoft.com.br.netshoes.model.productinfo;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "similarResults",
    "value",
    "label",
    "similarSearch"
})
public class Detail {

    @JsonProperty("similarResults")
    private String similarResults;
    @JsonProperty("value")
    private String value;
    @JsonProperty("label")
    private String label;
    @JsonProperty("similarSearch")
    private String similarSearch;

    /**
     * 
     * @return
     *     The similarResults
     */
    @JsonProperty("similarResults")
    public String getSimilarResults() {
        return similarResults;
    }

    /**
     * 
     * @param similarResults
     *     The similarResults
     */
    @JsonProperty("similarResults")
    public void setSimilarResults(String similarResults) {
        this.similarResults = similarResults;
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

    /**
     * 
     * @return
     *     The label
     */
    @JsonProperty("label")
    public String getLabel() {
        return label;
    }

    /**
     * 
     * @param label
     *     The label
     */
    @JsonProperty("label")
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 
     * @return
     *     The similarSearch
     */
    @JsonProperty("similarSearch")
    public String getSimilarSearch() {
        return similarSearch;
    }

    /**
     * 
     * @param similarSearch
     *     The similarSearch
     */
    @JsonProperty("similarSearch")
    public void setSimilarSearch(String similarSearch) {
        this.similarSearch = similarSearch;
    }

}
