package nejati.me.service.model.comics.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Variant {

    @SerializedName("resourceURI")
    @Expose
    var resourceURI: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null

}
