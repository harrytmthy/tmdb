package com.harrytmthy.tmdb.base;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version BaseContract, v 0.1 2019-12-31 10:48 by Harry Timothy
 */
public interface BaseContract {

    interface BaseView<S extends BaseState> {

        void render(S state);

    }

    interface BasePresenter<A extends BaseAction, S extends BaseState> {

        void bind(BaseView<S> view);

        void unbind();

        void doAction(A action);

    }

}