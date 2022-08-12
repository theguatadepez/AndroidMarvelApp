package com.example.marvelapiapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.marvelapiapp.data.network.SuperHeroAPI
import com.example.marvelapiapp.data.repositories.ApiException
import com.example.marvelapiapp.data.repositories.SuperHeroRepository
import com.example.marvelapiapp.ui.viewmodel.SuperHeroViewModel
import com.example.marvelapiapp.utils.getOrAwaitValue
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class SuperHeroViewModelUnitTest : MockWebServerBaseTest() {

    private lateinit var mSuperHeroViewModel: SuperHeroViewModel
    private lateinit var mBrkenSuperHeroViewModel: SuperHeroViewModel
    private val testDispatcher = TestCoroutineDispatcher()
    override fun isMockServerEnabled() = true

    private var mMockAPI = Mockito.mock(SuperHeroAPI::class.java)

    private var mMockSuperHeroRepository = Mockito.mock(SuperHeroRepository::class.java)

    @Mock
    private lateinit var mBrokenMockSuperHeroRepository: SuperHeroRepository

    @get:Rule
    val mInstantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        mMockAPI = provideTestApiService()
        mBrokenMockSuperHeroRepository = SuperHeroRepository(mMockAPI)
        mSuperHeroViewModel = SuperHeroViewModel(mMockSuperHeroRepository)
        mBrkenSuperHeroViewModel = SuperHeroViewModel(mBrokenMockSuperHeroRepository)
    }

    /**
     *
     * fun getSuperHeroes(mLimit: Int)
     *
     */

    @Test
    fun getSuperHeroesWorkingFine(){

        val mSuperHeroList = getDummySuperHeroList()

        runBlocking {
            Mockito.`when`(mMockSuperHeroRepository.getSuperHeroList(2)).thenReturn(mSuperHeroList)
            mSuperHeroViewModel.getSuperHeroes(2)
            val mSuperHeroData = mSuperHeroViewModel.superHeroesData.getOrAwaitValue()
            assert(mSuperHeroData.size == 2)
            assert(mSuperHeroData == mSuperHeroList)
        }
    }

    @Test(expected = ApiException::class)
    fun getSuperHeroApiExceptionHandling(){

        val observer = mockk<Observer<Any>> { every { onChanged(any()) } just Runs}

        runBlocking {

            mBrkenSuperHeroViewModel.errorLivedata.observeForever(observer)
            mockHttpResponse(409)
            Mockito.`when`(mBrokenMockSuperHeroRepository.getSuperHeroList(2)).thenThrow(
                ApiException("")
            )
            mBrkenSuperHeroViewModel.getSuperHeroes(2)
        }
        verify {
            observer.onChanged("409")
        }
    }

    /**
     *
     * fun getSuperHeroDetail(superHeroID: Int)
     *
     */

    @Test
    fun getSuperHeroDetailWorkingFine(){

        val mSuperHeroDetail = getDummySuperHeroDetail()

        runBlocking {
            Mockito.`when`(mMockSuperHeroRepository.getSuperHeroDetails(1011198)).thenReturn(mSuperHeroDetail)
            mSuperHeroViewModel.getSuperHeroDetail(1011198)
            val mSuperHeroData = mSuperHeroViewModel.superHeroDetail.getOrAwaitValue()
            assert(mSuperHeroData == mSuperHeroDetail)
        }
    }

    @Test
    fun getSuperHeroDetailWrongIDWorkingFine(){

        val mSuperHeroDetail = getDummySuperHeroDetail()

        runBlocking {
            Mockito.`when`(mMockSuperHeroRepository.getSuperHeroDetails(1011198)).thenReturn(mSuperHeroDetail)
            mSuperHeroViewModel.getSuperHeroDetail(123)
            val mSuperHeroData = mSuperHeroViewModel.superHeroDetail.getOrAwaitValue()
            assert(mSuperHeroData != mSuperHeroDetail)
        }
    }

    @Test(expected = ApiException::class)
    fun getSuperHeroDetailApiExceptionHandling(){

        val observer = mockk<Observer<Any>> { every { onChanged(any()) } just Runs}

        runBlocking {

            mBrkenSuperHeroViewModel.errorLivedata.observeForever(observer)
            mockHttpResponse(409)
            Mockito.`when`(mBrokenMockSuperHeroRepository.getSuperHeroDetails(2)).thenThrow(
                ApiException("")
            )
            mBrkenSuperHeroViewModel.getSuperHeroDetail(2)
        }
        verify {
            observer.onChanged("409")
        }
    }

}