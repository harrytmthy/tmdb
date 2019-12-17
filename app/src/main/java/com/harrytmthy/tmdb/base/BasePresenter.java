package com.harrytmthy.tmdb.base;

import androidx.databinding.ObservableField;
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
public abstract class BasePresenter<A extends BaseAction, S extends BaseState> {

    private BaseView<S> view;

    private final CompositeDisposable compositeDisposable;

    private final ObservableTransformer<A, S> dispatcher;

    private final PublishSubject<A> action;

    public final ObservableField<S> state;

    protected BasePresenter() {
        compositeDisposable = new CompositeDisposable();
        dispatcher = dispatch();
        action = PublishSubject.create();
        state = new ObservableField<>();
    }

    abstract protected ObservableTransformer<A, S> dispatch();

    public void doAction(A action) {
        this.action.onNext(action);
    }

    public void bind(BaseView<S> view) {
        this.view = view;
        compositeDisposable.add(action.compose(dispatcher)
            .subscribe(this.view::render));
    }

    public void unbind() {
        view = null;
        if(compositeDisposable == null) return;
        compositeDisposable.dispose();
    }

}