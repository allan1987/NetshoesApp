package mnidersoft.com.br.netshoes.model.productinfo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import mnidersoft.com.br.netshoes.model.Badge;
import mnidersoft.com.br.netshoes.model.Price;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Value {

    @JsonProperty("description")
    private String description;
    @JsonProperty("name")
    private String name;
    @JsonProperty("gallery")
    private List<Gallery> gallery = new ArrayList<Gallery>();
    @JsonProperty("characteristics")
    private List<Characteristic> characteristics = new ArrayList<Characteristic>();
    @JsonProperty("price")
    private Price price;
    @JsonProperty("badge")
    private Badge badge;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Gallery> getGallery() {
        return this.gallery;
    }

    public void setGallery(List<Gallery> gallery) {
        this.gallery = gallery;
    }

    public List<Characteristic> getCharacteristics() {
        return this.characteristics;
    }

    public void setCharacteristics(List<Characteristic> characteristics) {
        this.characteristics = characteristics;
    }

    public Price getPrice() {
        return this.price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Badge getBadge() {
        return this.badge;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }
}