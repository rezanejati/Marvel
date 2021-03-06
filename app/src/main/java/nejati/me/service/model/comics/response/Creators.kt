package nejati.me.service.model.comics.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Creators {

    @SerializedName("available")
    @Expose
    var available: Int? = null
    @SerializedName("collectionURI")
    @Expose
    var collectionURI: String? = null
    @SerializedName("items")
    @Expose
    var items: List<Item>? = null
    @SerializedName("returned")
    @Expose
    var returned: Int? = null

}
