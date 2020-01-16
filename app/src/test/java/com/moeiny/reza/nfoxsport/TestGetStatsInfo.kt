package com.moeiny.reza.nfoxsport



import com.moeiny.reza.nfoxsport.model.entity.Stats
import com.moeiny.reza.nfoxsport.presenter.MatchService
import com.moeiny.reza.nfoxsport.utils.FoxSportCallback
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import java.util.concurrent.CountDownLatch

class TestGetStatsInfo {
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testGetStatsInfo() {

        val lock =  CountDownLatch(1)
        lateinit var stats: Stats
        var team_Id="55015"
        var player_Id="114437"


        MatchService.getStatsInfo(team_Id,player_Id,object : FoxSportCallback<Stats, Throwable> {

            override fun onSuccess(result: Stats) {
                Assert.assertNotNull(result)
                stats = result
                var t=stats.career_stats.toString()
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

        stats.let {
            Assert.assertEquals(it.full_name, "Cameron Munster")
            Assert.assertEquals(it.short_name, "C. Munster")
            Assert.assertEquals(it.id, 114437)
        }
    }
}