package online.xiaohei.rxjava;


import org.junit.Test;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

//RxJava is Reactive eXtend for Java VM
public class HWObservable {
    @Test
    public void helloword() {
        Observable<String> myObservable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext("Hello, world");
                        subscriber.onCompleted();
                    }
                }
        );
        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        };

        myObservable.subscribe(mySubscriber);
    }

    @Test
    public void simplerHelloword() {
        // Ob.just发一个消息然后完成
        Observable<String> myOb = Observable.just("Hello word");
        // 不关心onComplete and onError的话,用更简单的类来定义onNext()
        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        };
        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        };
        Action0 onCompletAction = new Action0() {
            @Override
            public void call() {

            }
        };
        myOb.subscribe(onNextAction, onErrorAction, onCompletAction);
    }

    public void simplerHelloword2() {
        // Ob.just发一个消息然后完成
        Observable.just("Hello word").subscribe(
                new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                    }
                });
    }

    @Test
    public void simplerHW3() {
        Observable.just("Hello word")
                .map(s -> s.hashCode())
                .map(i -> Integer.toString(i))
                .subscribe(s -> System.out.println(s));
    }
}
