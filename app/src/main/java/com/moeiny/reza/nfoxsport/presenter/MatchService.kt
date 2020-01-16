package com.moeiny.reza.nfoxsport.presenter




import com.moeiny.reza.nfoxsport.model.entity.Match
import com.moeiny.reza.nfoxsport.model.entity.Stats
import com.moeiny.reza.nfoxsport.model.repository.MatchRepositoryProvider
import com.moeiny.reza.nfoxsport.utils.FoxSportCallback
import com.moeiny.reza.nfoxsport.utils.okHttpClientGETBuilder
import com.moeiny.reza.nfoxsport.utils.retrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

open class MatchService private constructor() {

    companion object {
        private var compositeDisposable: CompositeDisposable = CompositeDisposable()

        fun getMatchesInfo(block: FoxSportCallback<List<Match>, Throwable>) {
            val disposableService: Disposable = MatchRepositoryProvider.provideMatchRepository(retrofit(okHttpClientGETBuilder()))
                .getMatchesInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .observeOn(Schedulers.computation())
                    .subscribe(
                            { result ->
                                block.onSuccess(result = result)
                            },
                            {error ->
                                block.onError(error)
                            },
                            {
                                block.onComplete()
                            }
                    )

            compositeDisposable.add(disposableService)

        }

        fun getStatsInfo(team_Id:String,player_Id:String,block: FoxSportCallback<Stats, Throwable>) {
            val disposableService: Disposable = MatchRepositoryProvider.provideMatchRepository(retrofit(okHttpClientGETBuilder()))
                .getStatsInfo(team_Id,player_Id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
            //    .observeOn(Schedulers.computation())
                .subscribe(
                    { result ->
                        block.onSuccess(result = result)
                    },
                    {error ->
                        block.onError(error)
                    },
                    {
                        block.onComplete()
                    }
                )

            compositeDisposable.add(disposableService)

        }

    }

}