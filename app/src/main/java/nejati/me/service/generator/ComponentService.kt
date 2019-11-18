package nejati.me.service.generator

import dagger.Component
import nejati.me.service.di.component.NetComponent
import nejati.me.service.scope.CustomScope

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
@CustomScope
@Component(dependencies = [NetComponent::class])
interface ComponentService {

    /**
     * @param singletonService
     */
    fun inject(singletonService: SingletonService)
}
