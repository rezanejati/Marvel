package nejati.me.service.model.comics.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Date {

    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("date")
    @Expose
    var date: String? = null

}
