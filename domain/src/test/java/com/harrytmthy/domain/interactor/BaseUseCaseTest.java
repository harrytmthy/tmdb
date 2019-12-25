package com.harrytmthy.domain.interactor;

import com.harrytmthy.domain.base.BaseUseCase;
import com.harrytmthy.domain.executor.PostExecutionThread;
import com.harrytmthy.domain.executor.ThreadExecutor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.TestScheduler;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version BaseUseCaseTest, v 0.1 2019-12-25 10:52 by Harry Timothy
 */
@RunWith(MockitoJUnitRunner.class)
public class BaseUseCaseTest {

    private BaseUseCaseTestClass useCase;

    private CompositeDisposable compositeDisposable;

    @Mock private ThreadExecutor mockThreadExecutor;

    @Mock private PostExecutionThread mockPostExecutionThread;

    @Before
    public void setUp() {
        this.useCase = new BaseUseCaseTestClass(mockThreadExecutor, mockPostExecutionThread);
        compositeDisposable = new CompositeDisposable();
        given(mockPostExecutionThread.getScheduler()).willReturn(new TestScheduler());
    }

    @Test
    public void execute_isCalled() {
        compositeDisposable.add(useCase.execute(new Params()).subscribe());
        compositeDisposable.dispose();
        assertTrue(compositeDisposable.isDisposed());
    }

    private static class BaseUseCaseTestClass extends BaseUseCase<Object, Params> {

        BaseUseCaseTestClass(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
            super(threadExecutor, postExecutionThread);
        }

        @Override
        protected Observable<Object> buildUseCaseObservable(Params params) {
            return Observable.empty();
        }

        @Override
        public Observable<Object> execute(Params params) {
            return super.execute(params);
        }

    }

    private static class Params {}

}