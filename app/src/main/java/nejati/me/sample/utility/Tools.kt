package nejati.me.sample.utility

import nejati.me.sample.BuildConfig
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
object Tools{

    fun getMd5Hash(input: String): String? {
        try {
            val md = MessageDigest.getInstance("MD5")

            val messageDigest = md.digest(input.toByteArray())

            val no = BigInteger(1, messageDigest)

            var hashtext = no.toString(16)

            while (hashtext.length < 32) {
                hashtext = "0$hashtext"
            }
            return hashtext

        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException(e)
        }
    }

     fun marvelHashGenaretor(timestamp: Long): String? {
        val toHash: String = timestamp.toString().plus(BuildConfig.PRIVATE_KEY).plus(BuildConfig.PUBLIC_KEY)
        return getMd5Hash(toHash)
    }

}

