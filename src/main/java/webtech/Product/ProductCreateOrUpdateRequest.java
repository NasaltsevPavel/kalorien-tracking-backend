package webtech.Product;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class ProductCreateOrUpdateRequest {

    private Long id;
   @Size(min = 3 , message = "Please provide a name with 3 characters or more.")
    private String name;
   @Positive(message = "The kcal number can't be negativ")
   @NotNull(message = "The kcal number must not be null")
    private int kcal;
    @NotNull(message = "The type must not be null")
    private String type;

    public ProductCreateOrUpdateRequest(String name, int kcal, String type) {
        this.name = name;
        this.kcal = kcal;
        this.type = type;
    }

    public ProductCreateOrUpdateRequest() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
