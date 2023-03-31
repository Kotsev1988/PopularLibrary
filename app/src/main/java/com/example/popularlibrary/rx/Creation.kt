package com.example.popularlibrary.rx

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit
import kotlin.random.Random


class Creation {

    fun exec() {
        Consumer(producer = Producer()).exec()

//        Observable.just("1", "2", "3").subscribe({
//                println("onNext: $it")
//            }, { e ->
//            println("onError: ${e.message}")
//        }, {
//            println("onComplete")
//        })

    }

    class Producer() {

        fun just(): Observable<String> {
            return Observable.just("1", "2", "3")
        }
    }




    class Consumer(val producer: Producer) {

        fun exec(){
            execFlatMap()
        }


        fun execFlatMap() {
            producer.just()
                .switchMap {
                    val delay = Random.nextInt(1000).toLong()
                    return@switchMap Observable.just(it + "x").delay(delay,
                        TimeUnit.MILLISECONDS)
                }
                .subscribe({ s ->
                    println("RXonNext: $s")
                }, {
                    println("RXonError: ${it.message}")
                })
        }

    }
}