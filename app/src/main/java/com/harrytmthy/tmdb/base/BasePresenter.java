package com.harrytmthy.tmdb.base;

import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.PublishSubject;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version BasePresenter, v 0.1 2019-12-13 09:40 by Harry Timothy
 *
 * @param <A> Action type.
 * @param <S> State type.
 */
public abstract class BasePresenter<A extends BaseAction, S extends BaseState> implements BaseContract.BasePresenter<A, S> {

    protected BaseContract.BaseView<S> view;

    private final CompositeDisposable compositeDisposable;

    private final ObservableTransformer<A, S> dispatcher;

    private final PublishSubject<A> action;

    protected BasePresenter() {
        compositeDisposable = new CompositeDisposable();
        dispatcher = dispatch();
        action = PublishSubject.create();
    }

    abstract protected ObservableTransformer<A, S> dispatch();

    @Override
    public void doAction(A action) {
        this.action.onNext(action);
    }

    @Override
    public void bind(BaseContract.BaseView<S> view) {
        this.view = view;
        this.compositeDisposable.add(action.compose(dispatcher)
            .subscribe(state -> {
                this.view.render(state);
            }));
    }

    @Override
    public void unbind() {
        this.view = null;
        this.compositeDisposable.dispose();
    }

}