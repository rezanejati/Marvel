package nejati.me.service.listener
/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
interface OnServiceStatus<T> {

    /**
     * @param t the object when service have a response
     */
    fun onReady(t: T)

    /**
     * @param message  the message when service have an error
     */
    fun onError(message: String)

    /**
     *
     * @param message if Http Code Status not equal 200 This method will call
     */
    fun onHttpCodeStatus(status: Int)
}
