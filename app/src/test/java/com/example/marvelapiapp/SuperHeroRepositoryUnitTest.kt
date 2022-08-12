package com.example.marvelapiapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.marvelapiapp.data.model.SuperHero
import com.example.marvelapiapp.data.model.SuperHeroDetail
import com.example.marvelapiapp.data.network.SuperHeroAPI
import com.example.marvelapiapp.data.repositories.ApiException
import com.example.marvelapiapp.data.repositories.SuperHeroRepository
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations


@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class SuperHeroRepositoryUnitTest : MockWebServerBaseTest() {

    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var mSuperHeroRepository: SuperHeroRepository

    override fun isMockServerEnabled() = true

    @Mock
    private lateinit var mMockAPI: SuperHeroAPI

    @get:Rule
    val mException: ExpectedException = ExpectedException.none()

    @get:Rule
    val mInstantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        mMockAPI = provideTestApiService()
        mSuperHeroRepository = SuperHeroRepository(mMockAPI)
    }


    /**
     *
     * suspend fun getSuperHeroList(): List<SuperHero> Unit tests
     *
     * */

    @Test
    fun getSuperHeroListWorkingFine(){

        val mSuperHeroList: List<SuperHero> = getDummySuperHeroList()

        runBlocking {
            mockHttpResponse("herolistresponse.json",200)
            val mResult = mSuperHeroRepository.getSuperHeroList(2)
            assert(mSuperHeroList == mResult)
        }
    }

    @Test
    fun getSuperHeroListWrongList(){

        val mSuperHeroList: List<SuperHero> = getWrongDummySuperHeroList()

        runBlocking {
            mockHttpResponse("herolistresponse.json",200)
            val mResult = mSuperHeroRepository.getSuperHeroList(2)
            assert(mSuperHeroList != mResult)
        }
    }

    @Test
    fun getSuperHeroListLimitZero(){

        val mSuperHeroList: List<SuperHero> = getDummySuperHeroList()
        mException.expect(ApiException::class.java)
        mException.expectMessage("409")
        runBlocking {
            mockHttpResponse(409)
            val mResult = mSuperHeroRepository.getSuperHeroList(0)
            assertEquals("checking if equals..",null, mResult)
            assert(mSuperHeroList != mResult)
        }
    }

    @Test
    fun getSuperHeroListWrongLimitOver100(){

        val mSuperHeroList: List<SuperHero> = getDummySuperHeroList()
        mException.expect(ApiException::class.java)
        mException.expectMessage("409")
        runBlocking {
            mockHttpResponse(409)
            val mResult = mSuperHeroRepository.getSuperHeroList(1000)
            assertEquals("checking if equals..",null, mResult)
            assert(mSuperHeroList != mResult)
        }
    }

    /**
     *
     * suspend fun getSuperHeroDetails(superHeroID: Int): SuperHeroDetail
     *
     * */

    @Test
    fun getSuperHeroDetailsWorkingFine(){

        val mSuperHeroDetail: SuperHeroDetail = getDummySuperHeroDetail()

        runBlocking {
            mockHttpResponse("herodetailresponse.json",200)
            val mResult = mSuperHeroRepository.getSuperHeroDetails(1011198)
            assertEquals("checking if equals..",mSuperHeroDetail, mResult)
            assert(mSuperHeroDetail.id == mResult.id)
            assert(mSuperHeroDetail.description == mResult.description)
            assert(mSuperHeroDetail.comics == mResult.comics)
            assert(mSuperHeroDetail.events == mResult.events)
            assert(mSuperHeroDetail.series == mResult.series)
            assert(mSuperHeroDetail.image == mResult.image)
            assert(mSuperHeroDetail.urls == mResult.urls)
        }
    }

    @Test
    fun getSuperHeroDetailsWrongList(){

        val mSuperHeroDetail: SuperHeroDetail = getDummySuperHeroDetail()
        mException.expect(ApiException::class.java)
        mException.expectMessage("409")
        runBlocking {
            mockHttpResponse(409)
            val mResult = mSuperHeroRepository.getSuperHeroDetails(0)
            assertEquals("checking if equals..",null, mResult)
            assert(mSuperHeroDetail != mResult)
        }
    }

}