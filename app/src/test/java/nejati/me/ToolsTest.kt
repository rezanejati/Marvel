package nejati.me

import nejati.me.sample.utility.Tools
import org.junit.Assert
import org.junit.Test

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
class ToolsTest {

    @Test
    fun marvelHashTest() {
        Assert.assertEquals(
            "e8b8b35d399bba3c557e178e4137514d",
            Tools.marvelHashGenaretor(1573993040355L)
        )
    }

}
