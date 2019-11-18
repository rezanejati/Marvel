package nejati.me.service.model.comics.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Price {

    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("price")
    @Expose
    var price: Double? = null

}
