package com.moeiny.reza.nfoxsport


import com.moeiny.reza.nfoxsport.model.entity.Match
import com.moeiny.reza.nfoxsport.presenter.MatchService
import com.moeiny.reza.nfoxsport.utils.FoxSportCallback
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import java.util.concurrent.CountDownLatch

class TestGetMatchesInfo {
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testGetMatchesInfo() {

        val lock =  CountDownLatch(1)
        lateinit var matches: List<Match>


        MatchService.getMatchesInfo(object : FoxSportCallback<List<Match>, Throwable> {

            override fun onSuccess(result: List<Match>) {
                Assert.assertNotNull(result)
                matches = result
                lock.countDown()
            }

            override fun onError(error: Throwable?) {
                Assert.assertNotNull(error)
                lock.countDown()
            }

            override fun onComplete() {
                print("complete")
                lock.countDown()
            }
        })

        matches.let {
            Assert.assertEquals(it[0].match_id, "NRL20190101")
            Assert.assertEquals(it[0].stat_type, "fantasy_points")
            Assert.assertEquals(it[0].team_A.name, "Melbourne")
            Assert.assertEquals(it[0].team_B.short_name, "Broncos")
        }
    }
}