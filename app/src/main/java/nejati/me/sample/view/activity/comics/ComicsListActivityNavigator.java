package nejati.me.sample.view.activity.comics;

import nejati.me.service.model.comics.response.Result;

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
public interface ComicsListActivityNavigator {

    void onShowProgress();

    void onShowPaginationProgress();

    void onHideProgress();

    void onHidePaginationProgress();

    void onServerError();

    void onDetail(Result result);

    void onFabClick();

    void onNetworkError();

    void clearComicsAdapter();

    void onNoResultSearch();

    void onFoundResultSearch(int size);

}
