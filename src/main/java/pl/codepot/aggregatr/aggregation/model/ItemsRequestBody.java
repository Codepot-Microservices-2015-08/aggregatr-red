package pl.codepot.aggregatr.aggregation.model;

import java.util.List;

/**
 * Created by Pawelccb on 2015-08-28.
 */
public class ItemsRequestBody {

    private List<String> items;

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}
